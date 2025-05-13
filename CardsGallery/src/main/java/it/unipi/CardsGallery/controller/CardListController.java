package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.utilities.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class CardListController {

    @Autowired
    CardListService cardListService;

    @GetMapping("/name")
    public ResponseEntity<ResponseWrapper<List<CardList>>> getCardList(
            @RequestParam("cardListName") String cardListName,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        List<CardList> list = cardListService.searchCardList(cardListName, page);
        String response;
        if(list == null) {
            response = "search failed";
        } else {
            response = (!list.isEmpty()) ? "Card lists found successfully" : "No lists found";
        }
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<CardList>>> listsUser(
            @RequestParam("owner") String owner,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "password", required = false) String password
    ) {
        try{
            List<CardList> list = cardListService.userCardList(owner, page, username, password);
            String response = (list != null) ? "User's lists found successfully" : "User has no lists";
            return ResponseEntity.ok(new ResponseWrapper<>(response,list));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("You have to be a registered user to search for lists",null));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> createList (@Valid @RequestBody CardListDTO cardListDTO) {
        try{
            String listId = cardListService.createCardList(cardListDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Card List created successfully",listId));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("No registered account found",null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseWrapper<Void>> deleteList (@Valid @RequestBody DeleteCardListDTO deleteCardListDTO) {
        try{
            cardListService.deleteCardList(deleteCardListDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Card List deleted successfully",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PostMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> addCard (@Valid @RequestBody CardDTO cardDTO) {
        try{
            cardListService.insertIntoCardList(cardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Card added to the list",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @DeleteMapping("/card")
    public ResponseEntity<ResponseWrapper<Void>> deleteCard (@Valid @RequestBody CardDTO cardDTO) {
        try{
            cardListService.removeFromCardList(cardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Card removed from the list",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseWrapper<Void>> updateStatus (@Valid @RequestBody UpdateCardListDTO updateCardListDTO) {
        try{
            String status = (updateCardListDTO.isStatus() == Constants.PUBLIC) ? "public" : "private";
            cardListService.updateCardList(updateCardListDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("List set to " + status + " successfully",null));
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(),null));
        }catch(ExistingEntityException e){
            return ResponseEntity.ok(new ResponseWrapper<>(e.getMessage(),null));
        }
    }
}
