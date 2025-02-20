package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.ParametersException;

import java.util.List;

public interface UserService {
    void insertUser(User user) throws AuthenticationException, ExistingEntityException;
    void deleteUser(AuthDTO authDTO) throws AuthenticationException;
    String loginUser(LoginDTO loginDTO) throws AuthenticationException, ExistingEntityException;
    void updateUser(UpdateUserDTO user) throws AuthenticationException, ExistingEntityException;
    User profileUser(String username) throws ExistingEntityException;
    void followUser(UserDTO userDTO) throws AuthenticationException, ParametersException, ExistingEntityException;
    void unfollowUser(UserDTO userDTO) throws AuthenticationException, ExistingEntityException;
    void reactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException, ExistingEntityException;
    void deleteReactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException;
    void reactPost(PostReactionDTO postReactionDTO) throws AuthenticationException, ExistingEntityException;
    void deleteReactPost(PostReactionDTO postReactionDTO) throws AuthenticationException;
    Reaction getCardReact(String username, String cardId, TCG tcg);
    ResultPostReactionDTO getPostReact(String username, String owner, String title);
    DetailsUserDTO detailsUser(String username);
    List<String> recommendedUser(String username);
}

