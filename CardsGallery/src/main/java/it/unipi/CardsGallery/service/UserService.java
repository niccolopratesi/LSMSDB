package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.LoginDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.service.exception.AuthenticationException;

import java.util.Optional;

public interface UserService {
    public void insertUser(User user) throws AuthenticationException;
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException;
    public void loginUser(LoginDTO loginDTO) throws AuthenticationException;
    public Optional<User> profileUser(String username) throws AuthenticationException;
}
