package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.User;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
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
    public ResponseEntity<ResponseWrapper<User>> profileUser(@RequestParam("username") String username) {
        try{
            User user = userService.profileUser(username);
            return ResponseEntity.ok(new ResponseWrapper<>("User found successfully",user));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Username or Password wrong",null));
        }catch(ExistingEntityException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
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
        //!!! controllo che le codifiche hash siano uguali !!!
        try{
            userService.deleteUser(authDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Account deleted correctly",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateUser(@RequestBody UpdateUserDTO user) {
        try{
            userService.updateUser(user);
            return ResponseEntity.ok(new ResponseWrapper<>("Account updated correctly",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
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
