package it.unipi.CardsGallery.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Override
    public void authenticate(String username, String password) throws AuthenticationException {
        if(password.trim().equals("")) {
            throw new AuthenticationException("No password provided");
        }

        User u = userRepository.getUserByUsername(username);
        if(
                u == null ||
                !BCrypt.verifyer().verify(password.toCharArray(), u.getPassword().toCharArray()).verified
        ) {
            throw new AuthenticationException("Username or password is incorrect");
        }
    }

    @Override
    public void authenticateAdmin(AuthDTO authDTO) throws AuthenticationException, NoAdminException {
        if(authDTO.getPassword().trim().equals("")) {
            throw new AuthenticationException("No password provided");
        }
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
