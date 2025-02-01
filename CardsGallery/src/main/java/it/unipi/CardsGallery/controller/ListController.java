package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.CardList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {

    //Repository necessari

    @GetMapping("/{userId}")
    public CardList listsUser(@PathVariable int userId) {

        //...

        return null;
    }

    @PostMapping
    @ResponseBody
    public String createList () {

        //prelievo id utente dal body

        //prelievo nome lista dal body

        return "Creatin successful";
    }

    @DeleteMapping
    @ResponseBody
    public String deleteList () {

        //controllo che l'utente sia il propietario

        //prelievo id utente e id lista dal body

        return "Deleting successful";
    }

    @PostMapping("/card")
    @ResponseBody
    public String addCard () {

        //controllo che l'utente sia il propietario

        //prelievo id utente, id lista e id carta dal body

        return "Adding successful";
    }

    @DeleteMapping("/card")
    @ResponseBody
    public String deleteCard () {

        //controllo che l'utente sia il propietario

        //prelievo id utente, id lista e id carta dal body

        return "Deleting successful";
    }

    @PutMapping("/status")
    @ResponseBody
    public String updateStatus () {

        //controllo che l'utente sia il propietario

        //prelievo id utente, id lista e nuovo status dal body

        //lista.public = nuovo status (true/false);

        return "Updating successful";
    }
}
