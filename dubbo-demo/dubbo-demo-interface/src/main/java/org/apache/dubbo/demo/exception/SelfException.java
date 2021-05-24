package org.apache.dubbo.demo.exception;

public class SelfException extends RuntimeException {
    public SelfException() {
    }

    public SelfException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfException(String message) {
        super(message);
    }

    public SelfException(Throwable cause) {
        super(cause);
    }
}
