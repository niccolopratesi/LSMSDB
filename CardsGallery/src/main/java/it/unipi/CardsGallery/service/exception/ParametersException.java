package it.unipi.CardsGallery.service.exception;

public class ParametersException extends RuntimeException {
    public ParametersException(String message) {
        super(message);
    }
    public ParametersException(Exception e) {
        super(e);
    }
}
