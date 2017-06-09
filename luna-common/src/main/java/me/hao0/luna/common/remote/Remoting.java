package me.hao0.luna.common.remote;

import me.hao0.luna.common.support.Lifecycle;

/**
 * The remote communication interface
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public interface Remoting extends Lifecycle {

    /**
     * Register the remote communicate hook
     * @param remotingHook the remote hook
     */
    void registerHook(RemotingHook remotingHook);

}
