package it.unipi.CardsGallery.service.exception;

public class OwnershipException extends Exception
{
    public OwnershipException(Exception e) {
        super(e);
    }
    public OwnershipException(String message) {
        super(message);
    }
}
