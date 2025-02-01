package it.unipi.CardsGallery.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin") //!!! Forse non va messa neanche la barra !!!
public class AdminController {

    //!!! SERVE FARE I CONTROLLI PER VERIFICARE SIA UN ADMIN !!!

    //Repository necessari

    @PostMapping("/card")
    @ResponseBody
    public String newCard() {

        //...

        return "New Card";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String deleteCard() {

        //...

        return "Delete Card";
    }

    @PutMapping("/card")
    @ResponseBody
    public String updateCard() {

        //...

        return "Update Card";
    }

    @DeleteMapping("/user")
    @ResponseBody
    public String deleteUser() {

        //...

        return "Delete User";
    }

    @DeleteMapping("/post")
    @ResponseBody
    public String deletePost() {

        //...

        return "Delete Post";
    }
}
