package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardListRepository cardListRepository;

    public AuthenticationServiceImpl() {
    }

    @Override
    public void authenticate(AuthDTO authDTO) throws AuthenticationException {
        //!!! serve il .hash() della password !!!
        String hashedPassword = authDTO.getPassword();
        /*Optional<User> user = userRepository.findUserByUsernameAndPassword(authDTO.getUsername(), hashedPassword);
        if(user.isEmpty()) {
            throw new AuthenticationException("Username or password is incorrect");
        }*/
        if(!userRepository.existsUserByUsernameAndPassword(authDTO.getUsername(), hashedPassword)){
            throw new AuthenticationException("Username or password is incorrect");
        }
    }

    @Override
    public void listOwnership(String userId, String cardListId) throws AuthenticationException {
        /*Optional<CardList> cardList = cardListRepository.findByIdAndUserId(cardListId,userId);
        if(cardList.isEmpty()) {
            throw new AuthenticationException("User is not the owner of the card list");
        }*/
        if(!cardListRepository.existsByIdAndUserId(cardListId,userId)) {
            throw new AuthenticationException("User is not the owner of the card list");
        }
    }

    @Override
    public Boolean accountOwnership(AuthDTO authDTO) throws AuthenticationException {
        //!!! serve il .hash() della password !!!
        /*if(!userRepository.existsByIdAndUsernameAndPassword(authDTO.getId(),authDTO.getUsername(),authDTO.getPassword())) {
            throw new AuthenticationException("You are not the owner of the account");
        }*/
        User result = userRepository.findByIdAndUsernameAndPassword(authDTO.getId(),authDTO.getUsername(),authDTO.getPassword());
        if(result == null) {
            throw new AuthenticationException("You are not the owner of the account");
        }
        boolean isAdmin = result.getAdmin();
        return isAdmin;
    }

    @Override
    public void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException {
        User u = userRepository.findByUsernameAndPassword(authDTO.getUsername(), authDTO.getPassword());
        if(u == null)
            throw new AuthenticationException("User not found");
        boolean isAdmin = u.getAdmin();
        if(!isAdmin)
            throw new NoAdminException("User is not admin");
    }
}
