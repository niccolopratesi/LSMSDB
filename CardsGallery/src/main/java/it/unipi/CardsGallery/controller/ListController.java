package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.Card;
import it.unipi.CardsGallery.model.CardList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {

    //Repository necessari

    @GetMapping
    public CardList listsUser(@RequestParam("userId") int userId) {

        //...

        return null;
    }

    @PostMapping("/{id}")
    @ResponseBody
    public String createList (@RequestBody CardList list, @PathVariable("id") int id) {

        //...

        return "Creating successful";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteList (@RequestBody CardList list, @PathVariable("id") int id) {

        //controllo che l'utente sia il propietario

        //si potrebbe passare solo l'id della lista senza passare tutta la lista

        return "Deleting successful";
    }

    @PostMapping("/card/{idUser}/{idList}")
    @ResponseBody
    public String addCard (@RequestBody Card card, @PathVariable("idList") int idList, @PathVariable("idUser") int idUser) {

        //controllo che l'utente sia il propietario

        return "Adding successful";
    }

    @DeleteMapping("/card/{idUser}/{idList}")
    @ResponseBody
    public String deleteCard (@RequestBody Card card, @PathVariable("idList") int idList, @PathVariable("idUser") int idUser) {

        //controllo che l'utente sia il propietario

        //basterebbe l'id della carta da eliminare e non tutta la carta....!

        return "Deleting successful";
    }

    @PutMapping("/status/{idUser}")
    @ResponseBody
    public String updateStatus (@RequestBody CardList list, @PathVariable("idUser") int idUser) {

        //controllo che l'utente sia il propietario

        //lista.status = nuovo status (true/false) nel db

        return "Updating successful";
    }
}
