package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.DTO.LoginDTO;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.DTO.UserDTO;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseWrapper<Void>> registerUser(@RequestBody User user) {
        //!!! hash per codificare la password !!!
        try{
            userService.insertUser(user);
            return ResponseEntity.ok(new ResponseWrapper<>("Registration successful",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> loginUser(@RequestBody LoginDTO loginDTO) {
        //!!! controllo che le codifiche hash siano uguali !!!
        try{
            userService.loginUser(loginDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Login successful",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Username or Password wrong",null));
        }
    }

    //!!! Logout dovrebbe essere gestito solo lato client se non abbiamo cookie !!!

    @GetMapping
    public ResponseEntity<ResponseWrapper<Optional<User>>> profileUser(@RequestParam("username") String username) {
        try{
            Optional<User> user = userService.profileUser(username);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("User not found",null));
            }
            return ResponseEntity.ok(new ResponseWrapper<>("User found successfully",user));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Username or Password wrong",null));
        }
    }

    @GetMapping("/details")
    public User detailsUser(@RequestParam("username") String username) {

        //...

        return null;
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@RequestBody AuthDTO authDTO) {
        try{
            userService.deleteUser(authDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Account deleted correctly",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Account ownership failed",null));
        }
    }

    @PutMapping
    @ResponseBody
    public String updateUser(@RequestBody User user) {

        //!!! penso si possa lasciare tutto l'utente...è difficile sapere !!!
        //!!! a priori quali campisaranno modificati=>come registrazione  !!!

        //controllo che l'utente sia il propietario dell'account da aggiornare

        //prelievo id user e modifiche da body

        //update dati utente

        return "Update successful";
    }

    @PostMapping("/follow")
    @ResponseBody
    public String followUser(@RequestBody UserDTO userDTO) {

        //...

        return "Follow successful";
    }

    @DeleteMapping("/follow")
    @ResponseBody
    public String unfollowUser(@RequestBody UserDTO userDTO) {

        //...

        return "Unfollow successful";
    }
}
