package org.example.GameExceptions;

public class CityDoesNotExistException extends Exception {
    public CityDoesNotExistException(String message) {
        super(message);
    }

    public CityDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
