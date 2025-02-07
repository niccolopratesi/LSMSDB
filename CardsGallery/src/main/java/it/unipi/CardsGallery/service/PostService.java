package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import it.unipi.CardsGallery.service.exception.OwnershipException;

import java.util.List;

public interface PostService {
    public List<Post> getPostsByUser(String username, int page);
    public void createPost(PostDTO postDTO) throws AuthenticationException, ExistingEntityException;
    public void deletePostMember(DeletePostDTO dpDTO) throws AuthenticationException, OwnershipException;
}
