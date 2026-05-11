package org.example.game_exceptions;

public class EmptyLineException extends RuntimeException {
    public EmptyLineException(String message) {
        super(message);
    }

    public EmptyLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
