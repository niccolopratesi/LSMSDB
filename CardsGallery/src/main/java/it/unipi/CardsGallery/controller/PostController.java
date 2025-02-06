package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.PostDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    //Repository necessari

    @GetMapping
    public List<Post> postsUser (@RequestParam("username") int username) {

        //pagine di post?????

        //...

        return null;
    }

    @PostMapping
    @ResponseBody
    public String createPost (@RequestBody PostDTO postDTO) {

        //prelievo id utente dal body

        //prelievo post dal body

        return "Creation successful";
    }

    @DeleteMapping
    @ResponseBody
    public String deletePost(@RequestBody DeletePostDTO deletePostDTO) {

        //controllo che l'utente sia il propietario

        //prelievo id utente e id post dal body

        return "Delete successful";
    }

}
