package me.hao0.luna.server;

import io.netty.channel.Channel;
import me.hao0.luna.common.exception.SendRequestException;
import me.hao0.luna.common.exception.TimeoutException;
import me.hao0.luna.common.exception.TooManyRequestsException;
import me.hao0.luna.common.protocol.Command;
import me.hao0.luna.common.remote.Remoting;
import me.hao0.luna.common.remote.RequestProcessor;
import me.hao0.luna.common.util.Pair;
import me.hao0.luna.common.util.RequestCallback;
import java.util.concurrent.ExecutorService;

/**
 * The server communicate interface
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public interface Server extends Remoting {

    /**
     * Register a request processor
     * @param requestCode the request code
     * @param processor the request processor
     * @param executor the executor
     */
    void registerProcessor(final int requestCode, final RequestProcessor processor,
                           final ExecutorService executor);

    /**
     * Register the default request processor
     * @param processor the request processor
     * @param executor the executor
     */
    void registerDefaultProcessor(final RequestProcessor processor, final ExecutorService executor);

    /**
     * Get the request processor and the executor pair
     * @param requestCode the request code
     * @return the request processor and the executor pair
     */
    Pair<RequestProcessor, ExecutorService> getProcessorPair(final int requestCode);

    /**
     * Invoke synchronously
     * @param channel the channel
     * @param request the request command
     * @param timeoutMillis the timeout(ms)
     * @return The request command
     * @throws InterruptedException
     * @throws SendRequestException
     * @throws TimeoutException
     */
    Command invokeSync(final Channel channel, final Command request, final long timeoutMillis)
            throws InterruptedException, SendRequestException, TimeoutException;

    /**
     * Invoke asynchronously
     * @param channel the channel
     * @param request the request command
     * @param timeoutMillis the timeout(ms)
     * @param requestCallback the request callback
     * @return The request command
     * @throws InterruptedException
     * @throws SendRequestException
     * @throws TimeoutException
     * @throws TooManyRequestsException
     */
    void invokeAsync(final Channel channel, final Command request, final long timeoutMillis, final RequestCallback requestCallback)
            throws InterruptedException, TooManyRequestsException, TimeoutException, SendRequestException;

    /**
     * Invoke in the one way
     * @param channel the channel
     * @param request the request command
     * @param timeoutMillis the timeout(ms)
     * @return The request command
     * @throws InterruptedException
     * @throws SendRequestException
     * @throws TimeoutException
     * @throws TooManyRequestsException
     */
    void invokeOneway(final Channel channel, final Command request, final long timeoutMillis) throws InterruptedException, TooManyRequestsException, TimeoutException, SendRequestException;

    /**
     * Get the listening port locally
     * @return the listening port locally
     */
    int localListenPort();
}
