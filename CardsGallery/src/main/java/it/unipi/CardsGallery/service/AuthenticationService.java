package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;

public interface AuthenticationService {
    public void authenticate(AuthDTO authDTO) throws Exception;
}
