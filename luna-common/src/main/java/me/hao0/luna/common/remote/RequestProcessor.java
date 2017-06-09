package me.hao0.luna.common.remote;

import io.netty.channel.ChannelHandlerContext;
import me.hao0.luna.common.protocol.Command;

public interface RequestProcessor {

    /**
     * Process the request
     * @param ctx the channel context
     * @param request the request object
     * @return the commnd
     * @throws Exception
     */
    Command process(ChannelHandlerContext ctx, Command request) throws Exception;

    /**
     * Reject the request or not
     * @return reject or not
     */
    boolean reject();
}