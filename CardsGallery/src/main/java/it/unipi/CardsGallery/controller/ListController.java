package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.Card;
import it.unipi.CardsGallery.model.CardList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {

    //Repository necessari

    @GetMapping
    public CardList listsUser(@RequestParam("userId") int userId) {

        //!!! mettiamo le pagine????? !!!

        //...

        return null;
    }

    @PostMapping
    @ResponseBody
    public String createList (@RequestBody CardListDTO cardListDTO) {

        //...

        return "Creating successful";
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
    @ResponseBody
    public String updateStatus (@RequestBody UpdateCardListDTO updateCardListDTO) {

        //controllo che l'utente sia il propietario

        //lista.status = nuovo status (true/false) nel db

        return "Updating successful";
    }
}
