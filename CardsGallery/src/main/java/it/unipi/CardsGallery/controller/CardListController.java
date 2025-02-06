package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.impl.CardListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class CardListController {

    @Autowired
    CardListService cardListService;

    @GetMapping
    public CardList listsUser(@RequestParam("userId") int userId) {

        //!!! mettiamo le pagine????? !!!

        //...

        return null;
    }

    @PostMapping
    public ResponseEntity createList (@RequestBody CardListDTO cardListDTO) {
        try{
            cardListService.createCardList(cardListDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Card List created successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Failed to create Card List",null));
        }
    }

    @DeleteMapping
    @ResponseBody
    public String deleteList (@RequestBody DeleteCardListDTO deleteCardListDTO) {

        //controllo che l'utente sia il propietario

        //si potrebbe passare solo l'id della lista senza passare tutta la lista

        return "Deleting successful";
    }

    @PostMapping("/card")
    @ResponseBody
    public String addCard (@RequestBody CardDTO cardDTO) {

        //controllo che l'utente sia il propietario

        return "Adding successful";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String deleteCard (@RequestBody DeleteCardDTO deleteCardDTO) {

        //controllo che l'utente sia il propietario

        //basterebbe l'id della carta da eliminare e non tutta la carta....!

        return "Deleting successful";
    }

    @PutMapping("/status")
    public ResponseEntity updateStatus (@RequestBody UpdateCardListDTO updateCardListDTO) {
        try{
            String status = (updateCardListDTO.isStatus()) ? "public" : "private";
            cardListService.updateCardList(updateCardListDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("List set to " + status + " successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("Failed to set Card List status",null));
        }
    }
}
