package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;

import java.util.Optional;

public interface UserService {
    public void insertUser(User user) throws AuthenticationException;
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException;
    public String loginUser(LoginDTO loginDTO) throws AuthenticationException;
    public void updateUser(UpdateUserDTO user) throws AuthenticationException, ExistingEntityException;
    public User profileUser(String username) throws AuthenticationException, ExistingEntityException;
    public void followUser(UserDTO userDTO) throws AuthenticationException;
    public void unfollowUser(UserDTO userDTO) throws AuthenticationException;
    public void reactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException;
    public void deleteReactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException;
    public void reactPost(PostReactionDTO postReactionDTO) throws AuthenticationException;
    public void deleteReactPost(PostReactionDTO postReactionDTO) throws AuthenticationException;
    public Reaction getCardReact(String username, String cardId, TCG tcg);
}

