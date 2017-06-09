package me.hao0.luna.common.exception;

public class TooManyRequestsException extends InvokeException {

    private static final long serialVersionUID = 4326919581254519654L;

    public TooManyRequestsException(String message) {
        super(message);
    }
}