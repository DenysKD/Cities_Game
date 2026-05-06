package org.example.game_exceptions;

public class BotLoseGameException extends Exception {
    public BotLoseGameException(String message) {
        super(message);
    }

    public BotLoseGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
