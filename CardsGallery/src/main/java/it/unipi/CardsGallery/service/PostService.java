package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

import java.util.List;

public interface PostService {
    public List<Post> getPostsByUser(String username, int page);
    public void createPost(PostDTO postDTO) throws AuthenticationException;
    public void deletePost(DeletePostDTO dpDTO) throws AuthenticationException, NoAdminException;
}
