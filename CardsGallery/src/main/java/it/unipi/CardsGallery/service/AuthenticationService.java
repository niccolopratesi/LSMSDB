package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AuthenticationService {
    String authenticate(AuthDTO authDTO) throws AuthenticationException;
    void listOwnership(String userId, String cardListId) throws AuthenticationException;
    Boolean accountOwnership(AuthDTO authDTO) throws AuthenticationException;
    void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException;
}
