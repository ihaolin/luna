package me.hao0.luna.common.util;

/**
 * The async request's callback
 */
public interface RequestCallback {

    /**
     * Invoked when request finished
     * @param responseFuture the response future
     */
    void onComplete(final ResponseFuture responseFuture);
}