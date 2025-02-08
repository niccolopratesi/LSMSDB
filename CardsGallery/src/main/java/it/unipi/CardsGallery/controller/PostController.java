package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.CommonConstants;
import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.service.PostService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.OwnershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<ResponseWrapper<List<Post>>> postsUser (
            @RequestParam(required = true) String username,
            @RequestParam(defaultValue = "0") int page
    ) {
        List<Post> posts = postService.getPostsByUser(username, page);
        String msg = (posts == null) ? "empty page" : "search completed successfully";
        return ResponseEntity.ok(new ResponseWrapper<>(msg, posts));
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> createPost (@RequestBody PostDTO postDTO) {
        try{
            postService.createPost(postDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("post created",null));
        } catch(AuthenticationException | ExistingEntityException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deletePostMember(@RequestBody DeletePostDTO deletePostDTO) {
        try {
            postService.deletePostMember(deletePostDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("post deleted successfully",null));
        } catch (OwnershipException | AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

}
