package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.LoginDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.UserMongoTemplate;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMongoTemplate userMongoTemplate;

    @Autowired
    private AuthenticationService auth;

    public UserServiceImpl() {}

    @Override
    public void insertUser(User user) throws AuthenticationException{
        if(userRepository.existsUserByUsername(user.getUsername())){
            throw new AuthenticationException("Username already registered");
        }
        user.setId(null);
        user.setPosts(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException {
        auth.accountOwnership(authDTO);
        userRepository.deleteById(authDTO.getId());
    }

    @Override
    public void loginUser(LoginDTO loginDTO) throws AuthenticationException{
        /*Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if(userOptional.isEmpty()) {
            throw new AuthenticationException("Username or Password wrong");
        }*/
        if(!userRepository.existsUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword())){
            throw new AuthenticationException("Username or Password wrong");
        }
    }

    @Override
    public User profileUser(String username) throws AuthenticationException, ExistingEntityException {
        //auth.authenticate(authDTO);
        User user = userMongoTemplate.findUserByUsername(username);
        if(user == null) {
            throw new ExistingEntityException("User not found");
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws AuthenticationException {
        auth.accountOwnership(new AuthDTO(user.getUsername(),user.getPassword(), user.getId()));
        //!!! come gestiamo update admin e update password????? !!!
        user.setAdmin(false);
        userRepository.save(user);
    }
}
