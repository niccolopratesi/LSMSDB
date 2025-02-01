package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    //Repository necessari

    @GetMapping("/{userId}")
    public List<Post> postsUser (@PathVariable int userId) {

        //...

        return null;
    }

    @PostMapping
    @ResponseBody
    public String createPost () {

        //prelievo id utente dal body

        //prelievo post dal body

        return "Creation successful";
    }

    @DeleteMapping
    @ResponseBody
    public String deletePosts () {

        //controllo che l'utente sia il propietario

        //prelievo id utente e id post dal body

        return "Delete successful";
    }

}
