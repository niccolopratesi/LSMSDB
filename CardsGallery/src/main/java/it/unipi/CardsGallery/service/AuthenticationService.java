package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

import java.security.NoSuchAlgorithmException;

public interface AuthenticationService {
    public String authenticate(AuthDTO authDTO) throws AuthenticationException;
    public void listOwnership(String userId, String cardListId) throws AuthenticationException;
    public Boolean accountOwnership(AuthDTO authDTO) throws AuthenticationException;
    public void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException;
}
