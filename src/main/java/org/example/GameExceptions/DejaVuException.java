package org.example.GameExceptions;

public class DejaVuException extends Exception {
    public DejaVuException(String message) {
        super(message);
    }

    public DejaVuException(String message, Throwable cause) {
        super(message, cause);
    }
}
