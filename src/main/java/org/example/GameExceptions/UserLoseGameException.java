package org.example.GameExceptions;

public class UserLoseGameException extends Exception {
    public UserLoseGameException(String message) {
        super(message);
    }

    public UserLoseGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
