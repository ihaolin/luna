package me.hao0.luna.common.exception;

public class TimeoutException extends InvokeException {

    private static final long serialVersionUID = 4106899185095245979L;

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String addr, long timeoutMillis) {
        this(addr, timeoutMillis, null);
    }

    public TimeoutException(String addr, long timeoutMillis, Throwable cause) {
        super("wait response on the channel <" + addr + "> timeout, " + timeoutMillis + "(ms)", cause);
    }
}