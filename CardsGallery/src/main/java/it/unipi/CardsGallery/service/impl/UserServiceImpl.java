package it.unipi.CardsGallery.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.RequestType;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.PendingRequests;
import it.unipi.CardsGallery.pendingRequests.ReactionRequest;
import it.unipi.CardsGallery.pendingRequests.ReactionRequestData;
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
import it.unipi.CardsGallery.service.exception.ParametersException;
import it.unipi.CardsGallery.utilities.Constants;
import it.unipi.CardsGallery.utilities.OldUserReact;
import it.unipi.CardsGallery.utilities.ReactionCount;
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

    @Override
    public void insertUser(User user) throws AuthenticationException, ExistingEntityException{
        if(user.getUsername() == null || user.getUsername().trim().equals("")) {
            throw new ExistingEntityException("Please enter a username");
        }
        if(user.getPassword() == null || user.getPassword().trim().equals("")) {
            throw new ExistingEntityException("Please enter a password");
        }
        if(userRepository.existsUserByUsername(user.getUsername())){
            throw new AuthenticationException("Username already registered");
        }
        user.setId(null);
        user.createRegistrationDate();
        user.setPosts(new ArrayList<>());
        user.setAdmin(false);
        String hash = BCrypt.withDefaults().hashToString(Constants.BCRYPT_ROUNDS, user.getPassword().toCharArray());
        user.setPassword(hash);
        userRepository.save(user);

        UserNode userNode = new UserNode(user.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE_USER, userNode));
    }

    @Override
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException {
        auth.authenticate(authDTO.getUsername(), authDTO.getPassword());
        cardListRepository.deleteAllByUsername(authDTO.getUsername());
        userRepository.deleteByUsername(authDTO.getUsername());

        UserNode userNode = new UserNode(authDTO.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE_USER, userNode));
    }

    @Override
    public void loginUser(LoginDTO loginDTO) throws AuthenticationException, ExistingEntityException{
        if(loginDTO.getUsername().trim().equals("")) {
            throw new ExistingEntityException("Please enter a username");
        }
        if(loginDTO.getPassword().trim().equals("")) {
            throw new ExistingEntityException("Please enter a password");
        }
        authenticationService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @Override
    public User profileUser(String username) throws ExistingEntityException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new ExistingEntityException("User not found");
        }
        return user;
    }

    @Override
    public DetailsUserDTO detailsUser(String username) {
        int followersCount = userNodeRepository.getFollowersCount(username);
        int followingCount = userNodeRepository.getFollowingCount(username);
        int friendsCount = userNodeRepository.getFriendsCount(username);
        int postsCount = userNodeRepository.getPostsCount(username);

        return new DetailsUserDTO(followersCount, followingCount, friendsCount, postsCount);
    }

    @Override
    public void updateUser(UpdateUserDTO userDTO) throws AuthenticationException, ExistingEntityException {
        User user = userRepository.getUserByUsername(userDTO.getAuth().getUsername());
        if(user == null) {
            throw new ExistingEntityException("User not found");
        }

        if(
                !user.getUsername().equals(userDTO.getAuth().getUsername())  ||
                !BCrypt.verifyer().verify(userDTO.getAuth().getPassword().toCharArray(), user.getPassword().toCharArray()).verified
        ){
            throw new AuthenticationException("You are not the owner of the account");
        }

        if(userDTO.getNewUsername() != null && !userDTO.getNewUsername().trim().equals("")){
            if(userRepository.existsUserByUsername(userDTO.getNewUsername())){
                throw new AuthenticationException("Username already registered");
            }
            UserNode userNode = new UserNode(user.getUsername());
            cardListRepository.updateUsername(user.getUsername(), userDTO.getNewUsername());
            user.setUsername(userDTO.getNewUsername());

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE_USER, userNode, userDTO.getNewUsername()));
        }

        if(userDTO.getNewPassword() != null && !userDTO.getNewPassword().trim().equals("")){
            String hash = BCrypt.withDefaults().hashToString(Constants.BCRYPT_ROUNDS, userDTO.getNewPassword().toCharArray());
            user.setPassword(hash);
        }

        user.updateUser(userDTO);
        userRepository.save(user);
    }

    @Override
    public List<String> recommendedUser(String username) {
        return userNodeRepository.getRecommendedUsers(username);
    }

    @Override
    public void followUser(UserDTO userDTO) throws AuthenticationException, ParametersException, ExistingEntityException{
        if(userDTO.getUsername().trim().equals("")) {
            throw new ExistingEntityException("Insert a valid username to follow");
        }
        if(userDTO.getUsername().equals(userDTO.getLogin().getUsername())) {
            throw new ParametersException("You cannot follow yourself");
        }
        auth.authenticate(userDTO.getLogin().getUsername(), userDTO.getLogin().getPassword());
        Boolean result = userNodeRepository.follow(userDTO.getLogin().getUsername(), userDTO.getUsername());
        if(result == null || !result) {
            throw new ExistingEntityException("User not found");
        }
    }

    @Override
    public void unfollowUser(UserDTO userDTO) throws AuthenticationException, ExistingEntityException{
        auth.authenticate(userDTO.getLogin().getUsername(), userDTO.getLogin().getPassword());
        Boolean result = userNodeRepository.unfollow(userDTO.getLogin().getUsername(), userDTO.getUsername());
        if(result == null || !result) {
            throw new ExistingEntityException("Could not unfollow " + userDTO.getUsername());
        }
    }

    @Override
    public void reactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException, ExistingEntityException {
        if(cardReactionDTO.getReaction() == null) {
            throw new ExistingEntityException("Reaction not valid");
        }
        auth.authenticate(cardReactionDTO.getLogin().getUsername(), cardReactionDTO.getLogin().getPassword());
        OldUserReact oldUserReact = cardNodeRepository.react(cardReactionDTO.getLogin().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());
        if(oldUserReact == null || !oldUserReact.isResult()) {
            throw new ExistingEntityException("Could not react card: react already exists or card does not exist");
        }

        ReactionRequest reactionRequest = new ReactionRequest(cardReactionDTO.getCardId(), cardReactionDTO.getType());
        ReactionRequestData reactionRequestData = new ReactionRequestData(cardReactionDTO.getReaction());
        PendingRequests.addOrUpdateReaction(reactionRequest, reactionRequestData, cardReactionDTO.getReaction(), Constants.INCREMENT);
        if(oldUserReact.getOldReaction() != null) {
            ReactionRequestData oldReactionRequestData = new ReactionRequestData(oldUserReact.getOldReaction());
            PendingRequests.addOrUpdateReaction(reactionRequest, oldReactionRequestData, oldUserReact.getOldReaction(), Constants.DECREMENT);
        }
    }

    @Override
    public void deleteReactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException, ExistingEntityException {
        if(cardReactionDTO.getReaction() == null) {
            throw new ExistingEntityException("Reaction not valid");
        }
        auth.authenticate(cardReactionDTO.getLogin().getUsername(), cardReactionDTO.getLogin().getPassword());
        boolean ok = cardNodeRepository.reactDelete(cardReactionDTO.getLogin().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());
        if(!ok) {
            throw new ExistingEntityException("Could not delete reaction card");
        }

        ReactionRequest reactionRequest = new ReactionRequest(cardReactionDTO.getCardId(), cardReactionDTO.getType());
        PendingRequests.addOrUpdateReaction(reactionRequest, null, cardReactionDTO.getReaction(), Constants.DECREMENT);
    }

    @Override
    public void reactPost(PostReactionDTO postReactionDTO) throws AuthenticationException, ExistingEntityException {
        if(postReactionDTO.getReaction() == null) {
            throw new ExistingEntityException("Reaction not valid");
        }
        auth.authenticate(postReactionDTO.getLogin().getUsername(), postReactionDTO.getLogin().getPassword());
        boolean ok = postNodeRepository.react(postReactionDTO.getLogin().getUsername(), postReactionDTO.getTitle(), postReactionDTO.getOwner(), postReactionDTO.getReaction());
        if(!ok){
            throw new ExistingEntityException("Could not react post");
        }
    }

    @Override
    public void deleteReactPost(PostReactionDTO postReactionDTO) throws AuthenticationException, ExistingEntityException {
        auth.authenticate(postReactionDTO.getLogin().getUsername(), postReactionDTO.getLogin().getPassword());
        boolean ok = postNodeRepository.reactDelete(postReactionDTO.getLogin().getUsername(), postReactionDTO.getTitle(), postReactionDTO.getOwner());
        if(!ok){
            throw new ExistingEntityException("Could not delete the reaction");
        }
    }

    @Override
    public Reaction getCardReact(String username, String cardId, TCG tcg) {
        return cardNodeRepository.getReact(username, cardId, tcg).orElse(null);
    }

    @Override
    public ResultPostReactionDTO getPostReact(String username, String owner, String title) {
        List<ReactionCount> resultSet = postNodeRepository.getCounts(owner, title);
        if(resultSet.isEmpty()){
            return null;
        }
        Reaction reaction = postNodeRepository.getReact(username, owner, title);
        return new ResultPostReactionDTO(resultSet, reaction);
    }
}
