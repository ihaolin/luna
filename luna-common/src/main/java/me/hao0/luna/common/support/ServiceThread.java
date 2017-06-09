package me.hao0.luna.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The base class for background thread
 */
public abstract class ServiceThread implements Runnable, Lifecycle {

    protected static final Logger log = LoggerFactory.getLogger(ServiceThread.class);

    private static final long JOIN_TIME = 90 * 1000;

    protected final Thread thread;

    protected volatile boolean notified = false;

    protected volatile boolean stopped = false;

    public ServiceThread() {
        this.thread = new Thread(this, this.getServiceName());
    }

    @Override
    public void start() {
        this.thread.start();
    }

    @Override
    public void shutdown() {
        this.shutdown(false);
    }

    public void shutdown(final boolean interrupt) {

        this.stopped = true;

        log.info("shutdown thread " + this.getServiceName() + " interrupt " + interrupt);

        synchronized (this) {
            if (!this.notified) {
                this.notified = true;
                this.notify();
            }
        }

        try {

            if (interrupt) {
                this.thread.interrupt();
            }

            long beginTime = System.currentTimeMillis();

            this.thread.join(this.getJointime());

            long eclipseTime = System.currentTimeMillis() - beginTime;

            log.info("join thread " + this.getServiceName() + " eclipse time(ms) " + eclipseTime + " " + this.getJointime());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getJointime() {
        return JOIN_TIME;
    }

    public void stop() {
        this.stop(false);
    }

    public void stop(final boolean interrupt) {

        this.stopped = true;

        log.info("stop thread " + this.getServiceName() + " interrupt " + interrupt);

        synchronized (this) {
            if (!this.notified) {
                this.notified = true;
                this.notify();
            }
        }

        if (interrupt) {
            this.thread.interrupt();
        }
    }

    public void makeStop() {
        this.stopped = true;
        log.info("makeStop thread " + this.getServiceName());
    }

    public void wakeup() {
        synchronized (this) {
            if (!this.notified) {
                this.notified = true;
                this.notify();
            }
        }
    }

    protected void waitForRunning(long interval) {

        synchronized (this) {
            if (this.notified) {
                this.notified = false;
                this.onWaitEnd();
                return;
            }

            try {
                this.wait(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.notified = false;
                this.onWaitEnd();
            }
        }
    }

    protected void onWaitEnd() {

    }

    public boolean isStopped() {
        return stopped;
    }

    public abstract String getServiceName();
}