package org.example.game_exceptions;

public class EmptyLineException extends Exception {
    public EmptyLineException(String message) {
        super(message);
    }

    public EmptyLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
