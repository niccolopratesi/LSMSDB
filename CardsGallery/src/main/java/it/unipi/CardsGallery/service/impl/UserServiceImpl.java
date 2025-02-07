package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.LoginDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService auth;

    public UserServiceImpl() {}

    @Override
    public void insertUser(User user) throws AuthenticationException{
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());
        if(userOptional.isPresent()) {
            throw new AuthenticationException("Username already registered");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException {
        auth.authenticate(authDTO);
        userRepository.deleteById(authDTO.getId());
    }

    @Override
    public void loginUser(LoginDTO loginDTO) throws AuthenticationException{
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if(userOptional.isEmpty()) {
            throw new AuthenticationException("Username or Password wrong");
        }
    }

    @Override
    public Optional<User> profileUser(String username) throws AuthenticationException {


        return null;
    }

}
