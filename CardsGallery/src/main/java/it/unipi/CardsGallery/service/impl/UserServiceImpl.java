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
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void insertUser(User user) throws AuthenticationException, ExistingEntityException{
        if(user.getUsername() == null || user.getUsername().trim().equals("")) {
            throw new ExistingEntityException("Please enter a username");
        }
        if(userRepository.existsUserByUsername(user.getUsername())){
            throw new AuthenticationException("Username already registered");
        }
        user.setId(null);
        user.setPosts(new ArrayList<>());
        user.setAdmin(false);
        String hash = BCrypt.withDefaults().hashToString(Constants.BCRYPT_ROUNDS, user.getPassword().toCharArray());
        user.setPassword(hash);
        userRepository.save(user);

        UserNode userNode = new UserNode(user.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE, userNode));
    }

    @Override
    public void deleteUser(AuthDTO authDTO) throws AuthenticationException {
        auth.accountOwnership(authDTO);
        cardListRepository.deleteAllByUserId(authDTO.getId());
        userRepository.deleteById(authDTO.getId());

        UserNode userNode = new UserNode(authDTO.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, userNode));
    }

    @Override
    public String loginUser(LoginDTO loginDTO) throws AuthenticationException{
        return authenticationService.authenticate(new AuthDTO(loginDTO.getUsername(), loginDTO.getPassword()));
    }

    @Override
    public User profileUser(String username) throws ExistingEntityException {
        List<User> user = userRepository.findUserByUsername(username);
        if(user.isEmpty()) {
            throw new ExistingEntityException("User not found");
        }
        return user.get(0);
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

        if(userDTO.getNewUsername() != null){
            if(userRepository.existsUserByUsername(userDTO.getNewUsername())){
                throw new AuthenticationException("Username already registered");
            }
            UserNode userNode = new UserNode(user.getUsername());
            cardListRepository.updateUsername(user.getUsername(), userDTO.getNewUsername());
            user.setUsername(userDTO.getNewUsername());

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE, userNode, userDTO.getNewUsername()));
        }

        if(userDTO.getNewPassword() != null){
            String hash = BCrypt.withDefaults().hashToString(Constants.BCRYPT_ROUNDS, userDTO.getNewPassword().toCharArray());
            user.setPassword(hash);
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
    public List<String> reccomandedUser(String username) {
        return userNodeRepository.getReccomandedUsers(username);
    }

    @Override
    public void followUser(UserDTO userDTO) throws AuthenticationException{
        auth.authenticate(userDTO.getAuth());
        userNodeRepository.follow(userDTO.getAuth().getUsername(), userDTO.getUsername());
    }

    @Override
    public void unfollowUser(UserDTO userDTO) throws AuthenticationException{
        auth.authenticate(userDTO.getAuth());
        userNodeRepository.unfollow(userDTO.getAuth().getUsername(), userDTO.getUsername());
    }

    @Override
    public void reactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException {
        auth.authenticate(cardReactionDTO.getAuth());
        cardNodeRepository.react(cardReactionDTO.getAuth().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());

        ReactionRequest reactionRequest = new ReactionRequest(cardReactionDTO.getCardId(), cardReactionDTO.getType());
        ReactionRequestData reactionRequestData = new ReactionRequestData(cardReactionDTO.getReaction());
        PendingRequests.addOrUpdateReaction(reactionRequest, reactionRequestData, cardReactionDTO.getReaction(), Constants.INCREMENT);
    }

    @Override
    public void deleteReactCard(CardReactionDTO cardReactionDTO) throws AuthenticationException {
        auth.authenticate(cardReactionDTO.getAuth());
        cardNodeRepository.reactDelete(cardReactionDTO.getAuth().getUsername(), cardReactionDTO.getCardId(), cardReactionDTO.getType(), cardReactionDTO.getReaction());

        ReactionRequest reactionRequest = new ReactionRequest(cardReactionDTO.getCardId(), cardReactionDTO.getType());
        PendingRequests.addOrUpdateReaction(reactionRequest, null, cardReactionDTO.getReaction(), Constants.DECREMENT);
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

    @Override
    public Reaction getCardReact(String username, String cardId, TCG tcg) {
        return cardNodeRepository.getReact(username, cardId, tcg);
    }

    @Override
    public ResultPostReactionDTO getPostReact(String username, String owner, String title) {
        List<Map<Reaction, Integer>> list = postNodeRepository.getCounts(owner, title);
        if(list.isEmpty()){
            return null;
        }
        Map<Reaction, Integer> map = list.get(0);
        Reaction reaction = postNodeRepository.getReact(username, owner, title);
        return new ResultPostReactionDTO(map.get(Reaction.LIKE),map.get(Reaction.DISLIKE),map.get(Reaction.LOVE),map.get(Reaction.LAUGH),reaction);
    }
}
