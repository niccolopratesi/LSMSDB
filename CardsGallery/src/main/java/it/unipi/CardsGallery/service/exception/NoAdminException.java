package it.unipi.CardsGallery.service.exception;

public class NoAdminException extends RuntimeException {
    public NoAdminException(Exception e) {
        super(e);
    }
    public NoAdminException(String message) {
        super(message);
    }
}
