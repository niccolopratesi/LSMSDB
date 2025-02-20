package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.CardReactionDTO;
import it.unipi.CardsGallery.DTO.PostReactionDTO;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.DTO.ResultPostReactionDTO;
import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.service.UserService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/react")
public class ReactionController {

    @Autowired
    UserService userService;

    @GetMapping("/card")
    public ResponseEntity<ResponseWrapper<Reaction>> reactCard (@RequestParam("username") String username, @RequestParam("cardId") String cardId, @RequestParam("tcg") TCG tcg) {
        Reaction reaction = userService.getCardReact(username, cardId, tcg);
        String response = (reaction == null) ? "React not found" : "React found";
        return ResponseEntity.ok(new ResponseWrapper<>(response, reaction));
    }

    @GetMapping("/post")
    public ResponseEntity<ResponseWrapper<ResultPostReactionDTO>> reactPost (@RequestParam("username") String username, @RequestParam("owner") String owner, @RequestParam("title") String title) {
        ResultPostReactionDTO resultPostReactionDTO = userService.getPostReact(username, owner, title);
        String response = (resultPostReactionDTO == null) ? "Reactions not found" : "Reactions found";
        return ResponseEntity.ok(new ResponseWrapper<>(response, resultPostReactionDTO));
    }

    @PostMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> reactCard (@Valid @RequestBody CardReactionDTO cardReactionDTO) {
        try{
            userService.reactCard(cardReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React added",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> deleteReactCard (@Valid @RequestBody CardReactionDTO cardReactionDTO) {
        try{
            userService.deleteReactCard(cardReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React removed",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseWrapper<Void>> reactPost (@Valid @RequestBody PostReactionDTO postReactionDTO) {
        try{
            userService.reactPost(postReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React added",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping("/post")
    public ResponseEntity<ResponseWrapper<Void>> deleteReactPost (@Valid @RequestBody PostReactionDTO postReactionDTO) {
        try{
            userService.deleteReactPost(postReactionDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("React removed",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }
    }
}
