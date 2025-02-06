package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    public AuthenticationServiceImpl() {
    }

    @Override
    public void authenticate(AuthDTO authDTO) throws Exception {
        //!!! serve il .hash() della password !!!
        String hashedPassword = authDTO.getPassword();
        Optional<User> user = userRepository.findUserByUsernameAndPassword(authDTO.getUsername(), hashedPassword);
        if(user.isEmpty()) {
            throw new Exception();
        }
    }
}
