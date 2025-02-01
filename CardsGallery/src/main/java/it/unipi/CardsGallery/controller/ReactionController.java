package it.unipi.CardsGallery.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/react")
public class ReactionController {

    //Repository necessari

    @PostMapping("/card")
    @ResponseBody
    public String reactCard () {

        //...

        return "React Card Successful";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String reactCardDelete () {

        //...

        return "Delete React Card Successful";
    }

    @PostMapping("/post")
    @ResponseBody
    public String reactPost () {

        //...

        return "React Post Successful";
    }

    @DeleteMapping("/post")
    @ResponseBody
    public String reactPostDelete () {

        //...

        return "Delete React Post Successful";
    }
}
