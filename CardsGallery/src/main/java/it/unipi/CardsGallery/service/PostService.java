package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.service.exception.AuthenticationException;

import java.util.List;

public interface PostService {
    public List<Post> getPostsByUser(String username, int page);
    public void createPost(PostDTO postDTO) throws AuthenticationException;
}
