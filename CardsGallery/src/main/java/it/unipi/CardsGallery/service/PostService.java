package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.OwnershipException;

import java.util.List;

public interface PostService {
    List<Post> getPostsByUser(String username, int page);
    void createPost(PostDTO postDTO) throws AuthenticationException, ExistingEntityException;
    void deletePostMember(DeletePostDTO dpDTO) throws AuthenticationException, OwnershipException;
}
