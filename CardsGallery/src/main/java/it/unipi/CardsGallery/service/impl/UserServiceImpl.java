package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.LoginDTO;
import it.unipi.CardsGallery.DTO.UpdateUserDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.repository.neo4j.UserNodeRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private UserNodeRepository userNodeRepository;

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
        //delete all lists of this user
        cardListRepository.deleteAllByUserId(authDTO.getId());
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
        //User user = userMongoTemplate.findUserByUsername(username);
        List<User> user = userRepository.findUserByUsername(username);
        if(user.isEmpty()) {
            throw new ExistingEntityException("User not found");
        }
        return user.get(0);
    }

    @Override
    public void updateUser(UpdateUserDTO userDTO) throws AuthenticationException, ExistingEntityException {
        //auth.accountOwnership(new AuthDTO(user.getUsername(),user.getPassword(), user.getId()));
        //!!! update password????? !!!

        User user = userRepository.findById(userDTO.getAuth().getId()).orElse(null);
        if(user == null) {
            throw new ExistingEntityException("User not found");
        }

        // !!! hash password !!!
        if(!user.getUsername().equals(userDTO.getAuth().getUsername()) || !user.getPassword().equals(userDTO.getAuth().getPassword())){
            throw new AuthenticationException("You are not the owner of the account");
        }

        if(userDTO.getNewPassword() != null){
            //!!! hash password !!!
            user.setPassword(userDTO.getNewPassword());
        }
        if(userDTO.getNewUsername() != null){
            cardListRepository.updateUsername(user.getUsername(), userDTO.getNewUsername());
            user.setUsername(userDTO.getNewUsername());
        }

        user.setBirthDate(userDTO.getBirthDate());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setProfession(userDTO.getProfession());
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setSex(userDTO.getSex());

        userRepository.save(user);
    }
}
