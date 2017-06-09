package me.hao0.luna.common.exception;

public class SendRequestException extends InvokeException {



    public SendRequestException(String addr) {
        this(addr, null);
    }

    public SendRequestException(String addr, Throwable cause) {
        super("send request to <" + addr + "> failed", cause);
    }
}