package org.example.game_exceptions;

public class WrongCharacterCityException extends Exception {
    public WrongCharacterCityException(String message) {
        super(message);
    }

    public WrongCharacterCityException(String message, Throwable cause) {
        super(message, cause);
    }
}
