package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.CardReactionDTO;
import it.unipi.CardsGallery.DTO.PostReactionDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/react")
public class ReactionController {

    //Repository necessari

    @PostMapping("/card")
    @ResponseBody
    public String reactCard (@RequestBody CardReactionDTO cardReactionDTO) {

        //...

        return "React Card Successful";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String deleteReactCard (@RequestBody CardReactionDTO cardReactionDTO) {

        //...

        return "Delete React Card Successful";
    }

    @PostMapping("/post")
    @ResponseBody
    public String reactPost (@RequestBody PostReactionDTO postReactionDTO) {

        //...

        return "React Post Successful";
    }

    @DeleteMapping("/post")
    @ResponseBody
    public String deleteReactPost (@RequestBody PostReactionDTO postReactionDTO) {

        //...

        return "Delete React Post Successful";
    }
}
