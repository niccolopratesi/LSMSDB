package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.service.exception.AuthenticationException;

public interface AuthenticationService {
    public void authenticate(AuthDTO authDTO) throws AuthenticationException;
    public void listOwnership(String userId, String cardListId) throws AuthenticationException;
    public Boolean accountOwnership(AuthDTO authDTO) throws AuthenticationException;
}
