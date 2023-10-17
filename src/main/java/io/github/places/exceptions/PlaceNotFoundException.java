package io.github.places.exceptions;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(Long placeId) {
        super("Place not found with id: " + placeId);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
