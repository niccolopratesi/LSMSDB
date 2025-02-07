package it.unipi.CardsGallery.service.exception;

public class ExistingEntityException extends Exception {
    public ExistingEntityException(Exception e) {
        super(e);
    }
    public ExistingEntityException(String message) {
        super(message);
    }
}
