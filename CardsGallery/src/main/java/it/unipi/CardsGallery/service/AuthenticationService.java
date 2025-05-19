package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AuthenticationService {
    void authenticate(String username, String Password) throws AuthenticationException;
    void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException;
}
