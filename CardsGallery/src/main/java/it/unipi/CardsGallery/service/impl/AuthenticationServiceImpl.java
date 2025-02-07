package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
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
        Optional<User> user = userRepository.findUserByUsernameAndPassword(authDTO.getUsername(), hashedPassword);
        if(user.isEmpty()) {
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
}
