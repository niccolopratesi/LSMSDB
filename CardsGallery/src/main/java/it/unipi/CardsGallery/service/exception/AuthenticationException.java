package it.unipi.CardsGallery.service.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException(Exception e) {
        super(e);
    }
    public AuthenticationException(String message) {
        super(message);
    }
}
