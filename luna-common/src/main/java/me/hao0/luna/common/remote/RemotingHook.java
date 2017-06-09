package me.hao0.luna.common.remote;

import me.hao0.luna.common.protocol.Command;

public interface RemotingHook {

    /**
     * Invoke before sending a request
     * @param remoteAddr the remote addr
     * @param request the request
     */
    void doBeforeRequest(final String remoteAddr, final Command request);

    void doAfterResponse(final String remoteAddr, final Command request, final Command response);
}