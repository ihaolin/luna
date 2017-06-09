package me.hao0.luna.common.remote;

import me.hao0.luna.common.support.ServiceThread;
import me.hao0.luna.common.util.Pair;
import me.hao0.luna.common.util.ResponseFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * The abstract remote communication implementation
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public abstract class AbstractRemoting {

    private static Logger log = LoggerFactory.getLogger(AbstractRemoting.class);

    /**
     * The semaphore for oneway invocation up limit
     */
    protected final Semaphore semaphoreOneway;

    /**
     * The semaphore for async invocation up limit
     */
    protected final Semaphore semaphoreAsync;

    /**
     * The request code processor table
     * <p>
     *     <requestCode, <RequestProcessor, ExecutorService>>
     * </p>
     */
    protected final HashMap<Integer, Pair<RequestProcessor, ExecutorService>> processorTable = new HashMap<>(64);

    /**
     * The response table
     * <p>
     *     <opaque, ResponseFuture>
     * </p>
     */
    protected final ConcurrentHashMap<Integer, ResponseFuture> responseTable = new ConcurrentHashMap<>(256);

    /**
     * The netty event executor
     */
    protected final NettyEventExecutor nettyEventExecutor = new NettyEventExecutor();

    public AbstractRemoting(Semaphore semaphoreOneway, Semaphore semaphoreAsync) {
        this.semaphoreOneway = semaphoreOneway;
        this.semaphoreAsync = semaphoreAsync;
    }

    /**
     * Put a netty event
     * @param event the netty event
     */
    public void putNettyEvent(final NettyEvent event) {
        this.nettyEventExecutor.putNettyEvent(event);
    }

    /**
     * The netty event based on LinkedBlockingQueue
     * @see LinkedBlockingQueue
     */
    class NettyEventExecutor extends ServiceThread {

        private final LinkedBlockingQueue<NettyEvent> q = new LinkedBlockingQueue<NettyEvent>();

        private final int maxSize = 10000;

        public void putNettyEvent(final NettyEvent event) {
            if (this.q.size() <= maxSize) {
                this.q.add(event);
            } else {
                log.warn("event queue size[{}] enough, so drop this event {}", this.q.size(), event.toString());
            }
        }

        @Override
        public void run() {

            log.info(this.getServiceName() + " service started");

            final ChannelEventListener listener = AbstractRemoting.this.getChannelEventListener();

            while (!this.isStopped()) {
                try {
                    NettyEvent event = this.q.poll(3000, TimeUnit.MILLISECONDS);
                    if (event != null && listener != null) {
                        switch (event.getType()) {
                            case IDLE:
                                listener.onIdle(event.getRemoteAddr(), event.getChannel());
                                break;
                            case CLOSE:
                                listener.onClose(event.getRemoteAddr(), event.getChannel());
                                break;
                            case CONNECT:
                                listener.onConnect(event.getRemoteAddr(), event.getChannel());
                                break;
                            case EXCEPTION:
                                listener.onException(event.getRemoteAddr(), event.getChannel());
                                break;
                            default:
                                log.warn("Unknown netty event({}).", event);
                                break;

                        }
                    }
                } catch (Exception e) {
                    log.warn(this.getServiceName() + " service has exception. ", e);
                }
            }

            log.info(this.getServiceName() + " service end");
        }

        @Override
        public String getServiceName() {
            return NettyEventExecutor.class.getSimpleName();
        }
    }

    /**
     * Get the channel event listener
     * @return the channel event listener
     */
    public abstract ChannelEventListener getChannelEventListener();
}
