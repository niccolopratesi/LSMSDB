package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.CardReactionDTO;
import it.unipi.CardsGallery.DTO.PostReactionDTO;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/react")
public class ReactionController {

    @Autowired
    UserService userService;

    @PostMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> reactCard (@RequestBody CardReactionDTO cardReactionDTO) {
        try{
            userService.reactCard(cardReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React added",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> deleteReactCard (@RequestBody CardReactionDTO cardReactionDTO) {
        try{
            userService.deleteReactCard(cardReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React removed",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseWrapper<Void>> reactPost (@RequestBody PostReactionDTO postReactionDTO) {
        try{
            userService.reactPost(postReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React added",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping("/post")
    public ResponseEntity<ResponseWrapper<Void>> deleteReactPost (@RequestBody PostReactionDTO postReactionDTO) {
        try{
            userService.deleteReactPost(postReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React removed",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }
}
