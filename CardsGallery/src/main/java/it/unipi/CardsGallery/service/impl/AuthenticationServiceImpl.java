package it.unipi.CardsGallery.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
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

import java.util.Arrays;
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
    public String authenticate(AuthDTO authDTO) throws AuthenticationException {
        String password = authDTO.getPassword();

        User u = userRepository.getUserByUsername(authDTO.getUsername());
        if(
                u == null ||
                !BCrypt.verifyer().verify(password.toCharArray(), u.getPassword().toCharArray()).verified
        ) {
            throw new AuthenticationException("Username or password is incorrect");
        }
        return u.getId();
    }

    @Override
    public void listOwnership(String userId, String cardListId) throws AuthenticationException {
        if(!cardListRepository.existsByIdAndUserId(cardListId,userId)) {
            throw new AuthenticationException("You are not the owner of the card list");
        }
    }

    @Override
    public Boolean accountOwnership(AuthDTO authDTO) throws AuthenticationException {
        String password = authDTO.getPassword();
        User u = userRepository.getUserByIdAndUsername(authDTO.getId(), authDTO.getUsername());
        if(
                u == null ||
                !BCrypt.verifyer().verify(password.toCharArray(), u.getPassword().toCharArray()).verified
        ) {
            throw new AuthenticationException("You are not the owner of the account");
        }
        return u.getAdmin();
    }

    @Override
    public void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException {
        String password = authDTO.getPassword();
        User u = userRepository.getUserByUsername(authDTO.getUsername());
        if(
                u == null ||
                !BCrypt.verifyer().verify(password.toCharArray(), u.getPassword().toCharArray()).verified
        ) {
            throw new AuthenticationException("Username or password is incorrect");
        }
        boolean isAdmin = u.getAdmin();
        if(!isAdmin)
            throw new NoAdminException("User is not admin");
    }
}
