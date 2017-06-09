package me.hao0.luna.common.remote;

import io.netty.channel.Channel;

/**
 * The netty channel event listener
 */
public interface ChannelEventListener {

    /**
     * Listening on connect event
     * @param remoteAddr the remote address
     * @param channel the channel object
     */
    void onConnect(final String remoteAddr, final Channel channel);

    /**
     * Listening on close event
     * @param remoteAddr the remote address
     * @param channel the channel object
     */
    void onClose(final String remoteAddr, final Channel channel);

    /**
     * Listening on exception event
     * @param remoteAddr the remote address
     * @param channel the channel object
     */
    void onException(final String remoteAddr, final Channel channel);

    /**
     * Listening on idle event
     * @param remoteAddr the remote address
     * @param channel the channel object
     */
    void onIdle(final String remoteAddr, final Channel channel);
}