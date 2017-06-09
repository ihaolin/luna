package me.hao0.luna.common.exception;

public class InvokeException extends Exception {
    private static final long serialVersionUID = -5690687334570505110L;

    public InvokeException(String message) {
        super(message);
    }

    public InvokeException(String message, Throwable cause) {
        super(message, cause);
    }
}