package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    //Repository necessari

    @PostMapping("/registration")
    @ResponseBody
    public String registerUser() {

        //hash per codificare la password

        return "Registration successful";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginUser() {

        //controllo che le codifiche hash siano uguali

        return "Login successful";
    }

    //!!! Logout dovrebbe essere gestito solo lato client se non abbiamo cookie !!!

    /*@GetMapping("/{id}")
    public User profileUser(@PathVariable int id) {

        //...

        return null;
    }
    si può fare anche solo con username per prendere sia il proprio che quello degli altri
    */

    @GetMapping("/{username}")
    public User profileUser(@PathVariable int username) {

        //...

        return null;
    }

    @GetMapping("/details/{username}")
    public User detailsUser(@PathVariable int username) {

        //...

        return null;
    }

    @DeleteMapping
    @ResponseBody
    public String deleteUser() {

        //controllo che l'utente sia il propietario dell'account da eliminare

        //prelievo id user da body

        return "Delete successful";
    }

    @PutMapping
    @ResponseBody
    public String updateUser() {

        //controllo che l'utente sia il propietario dell'account da aggiornare

        //prelievo id user e modifiche da body

        //update dati utente

        return "Update successful";
    }

    @PostMapping("/follow")
    @ResponseBody
    public String followUser() {

        //...

        return "Follow successful";
    }

    @DeleteMapping("/follow")
    @ResponseBody
    public String unfollowUser() {

        //...

        return "Unfollow successful";
    }

}
