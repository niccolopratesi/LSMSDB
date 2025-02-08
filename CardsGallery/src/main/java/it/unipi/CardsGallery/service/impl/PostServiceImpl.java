package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.CommonConstants;
import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.controller.PostController;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.repository.mongo.UserRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.PostService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import it.unipi.CardsGallery.service.exception.OwnershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Post> getPostsByUser(String username, int page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE, Sort.by("id").ascending()); // Sorting optional
        List<Post> result = userRepository.findPostsByUsername(username, page*CommonConstants.PAGE_SIZE, CommonConstants.PAGE_SIZE);
        return result;
    }

    @Override
    public void createPost(PostDTO postDTO) throws AuthenticationException, ExistingEntityException {
        authenticationService.accountOwnership(postDTO.getAuth());
        Post post = postDTO.getPost();
        String id = postDTO.getAuth().getId();
        if(userRepository.existsByUsernameAndPostsTitle(postDTO.getAuth().getUsername(), postDTO.getPost().getTitle())) {
            throw new ExistingEntityException("post already exists");
        }
        userRepository.addPostToUser(id, post);
    }

    @Override
    public void deletePostMember(DeletePostDTO dpDTO) throws AuthenticationException, OwnershipException {
        authenticationService.authenticate(dpDTO.getAuth());
        if(userRepository.existsByUsernameAndPostsTitle(dpDTO.getAuth().getUsername(), dpDTO.getPostTitle())){
            userRepository.deletePostFromUser(dpDTO.getAuth().getUsername(), dpDTO.getPostTitle());
        } else {
            throw new OwnershipException("post not found");
        }
    }

}
