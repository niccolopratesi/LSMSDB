package it.unipi.CardsGallery.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.RequestType;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.PendingRequests;
import it.unipi.CardsGallery.pendingRequests.Request;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.repository.neo4j.CardNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.PostNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.UserNodeRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private UserNodeRepository userNodeRepository;

    @Autowired
    private CardNodeRepository cardNodeRepository;

    @Autowired
    private PostNodeRepository postNodeRepository;

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
        String hash = BCrypt.withDefaults().hashToString(10, user.getPassword().toCharArray());
        user.setPassword(hash);
        userRepository.save(user);

        UserNode userNode = new UserNode(user.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE, userNode));
    }

    @Override
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException {
        auth.accountOwnership(authDTO);
        //delete all lists of this user
        cardListRepository.deleteAllByUserId(authDTO.getId());
        userRepository.deleteById(authDTO.getId());

        UserNode userNode = new UserNode(authDTO.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, userNode));
    }

    @Override
    public String loginUser(LoginDTO loginDTO) throws AuthenticationException{
        /*Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if(userOptional.isEmpty()) {
            throw new AuthenticationException("Username or Password wrong");
        }*/
        return authenticationService.authenticate(new AuthDTO(loginDTO.getUsername(), loginDTO.getPassword()));
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

        if(
                !user.getUsername().equals(userDTO.getAuth().getUsername())  ||
                !BCrypt.verifyer().verify(user.getPassword().toCharArray(), user.getPassword().toCharArray()).verified
        ){
            throw new AuthenticationException("You are not the owner of the account");
        }

        if(userDTO.getNewPassword() != null){
            String hash = BCrypt.withDefaults().hashToString(10, userDTO.getNewPassword().toCharArray());
            user.setPassword(hash);
        }
        if(userDTO.getNewUsername() != null){
            UserNode userNode = new UserNode(user.getUsername());
            cardListRepository.updateUsername(user.getUsername(), userDTO.getNewUsername());
            user.setUsername(userDTO.getNewUsername());

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE, userNode, userDTO.getNewUsername()));
        }

        user.setBirthDate(userDTO.getBirthDate());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setProfession(userDTO.getProfession());
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setSex(userDTO.getSex());

        userRepository.save(user);
    }

    @Override
    public void followUser(UserDTO userDTO) throws AuthenticationException{
        auth.authenticate(userDTO.getAuth());
        userNodeRepository.follow(userDTO.getAuth().getId(), userDTO.getUsername());
    }

    @Override
    public void unfollowUser(UserDTO userDTO) throws AuthenticationException{
        auth.authenticate(userDTO.getAuth());
        userNodeRepository.unfollow(userDTO.getAuth().getId(), userDTO.getUsername());
    }

    @Override
    public void reactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException {
        auth.authenticate(cardReactionDTO.getAuth());
        cardNodeRepository.react(cardReactionDTO.getAuth().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());
    }

    @Override
    public void deleteReactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException {
        auth.authenticate(cardReactionDTO.getAuth());
        cardNodeRepository.reactDelete(cardReactionDTO.getAuth().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());
    }

    @Override
    public void reactPost(PostReactionDTO postReactionDTO) throws AuthenticationException {
        auth.authenticate(postReactionDTO.getAuth());
        postNodeRepository.react(postReactionDTO.getAuth().getUsername(), postReactionDTO.getTitle(), postReactionDTO.getOwner(), postReactionDTO.getReaction());
    }

    @Override
    public void deleteReactPost(PostReactionDTO postReactionDTO) throws AuthenticationException {
        auth.authenticate(postReactionDTO.getAuth());
        postNodeRepository.reactDelete(postReactionDTO.getAuth().getUsername(), postReactionDTO.getTitle(), postReactionDTO.getOwner(), postReactionDTO.getReaction());
    }
}
