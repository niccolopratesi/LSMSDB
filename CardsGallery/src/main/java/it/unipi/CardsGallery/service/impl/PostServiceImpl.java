package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.enums.RequestType;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.pendingRequests.PendingRequests;
import it.unipi.CardsGallery.pendingRequests.Request;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.PokemonCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.repository.mongo.YugiohCardMongoRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.PostService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.OwnershipException;
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Post> getPostsByUser(String username, int page) {
        if(page < 0) {
            page = 0;
        }
        return userRepository.findPostsByUsername(username, page*Constants.POST_PAGE_SIZE, Constants.POST_PAGE_SIZE);
    }

    @Override
    public void createPost(PostDTO postDTO) throws AuthenticationException, ExistingEntityException {
        authenticationService.accountOwnership(postDTO.getAuth());
        Post post = postDTO.getPost();
        String id = postDTO.getAuth().getId();
        if(postDTO.getPost().getTitle() == null || postDTO.getPost().getTitle().trim().equals("")) {
            throw new ExistingEntityException("Please enter a title");
        }
        if(userRepository.existsByUsernameAndPostsTitle(postDTO.getAuth().getUsername(), postDTO.getPost().getTitle())) {
            throw new ExistingEntityException("post already exists");
        }

        boolean result;

        switch (post.getType()) {
            case MAGIC:
                result = magicCardMongoRepository.existsById(post.getCardId());
                break;
            case POKEMON:
                result = pokemonCardMongoRepository.existsById(post.getCardId());
                break;
            case YUGIOH:
                result = yugiohCardMongoRepository.existsById(post.getCardId());
                break;
            default:
                throw new ExistingEntityException("Please enter card's Tcg correctly");
        }

        if (!result) {
            throw new ExistingEntityException("Card does not exist");
        }

        userRepository.addPostToUser(id, post);

        PostNode postNode = new PostNode(post.getTitle());
        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE, postNode, postDTO.getAuth().getUsername()));
    }

    @Override
    public void deletePostMember(DeletePostDTO dpDTO) throws AuthenticationException, OwnershipException {
        authenticationService.authenticate(dpDTO.getAuth());
        if(!userRepository.existsByUsernameAndPostsTitle(dpDTO.getAuth().getUsername(), dpDTO.getPostTitle())){
            throw new OwnershipException("post not found");
        }
        userRepository.deletePostFromUser(dpDTO.getAuth().getUsername(), dpDTO.getPostTitle());
        PostNode postNode = new PostNode(dpDTO.getPostTitle());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, postNode, dpDTO.getAuth().getUsername()));
    }
}
