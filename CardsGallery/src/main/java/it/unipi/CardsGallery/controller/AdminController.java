package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.AdminCardDTO;
import it.unipi.CardsGallery.DTO.AdminDelete;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin") //!!! Forse non va messa neanche la barra !!!
public class AdminController {

    //!!! SERVE FARE I CONTROLLI PER VERIFICARE SIA UN ADMIN !!!

    //Repository necessari

    @PostMapping("/card")
    @ResponseBody
    public String newCard(@RequestBody AdminCardDTO adminCardDTO) {

        //...

        return "New Card";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String deleteCard(@RequestBody AdminDelete adminDelete) {

        //...

        return "Delete Card";
    }

    @PutMapping("/card")
    @ResponseBody
    public String updateCard(@RequestBody AdminCardDTO adminCardDTO) {

        //...

        return "Update Card";
    }

    @DeleteMapping("/user")
    @ResponseBody
    public String deleteUser(@RequestBody AdminDelete adminDelete) {

        //...

        return "Delete User";
    }

    @DeleteMapping("/post")
    @ResponseBody
    public String deletePost(@RequestBody AdminDelete adminDelete) {

        //...

        return "Delete Post";
    }
}
