package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.PokemonCard;
import it.unipi.CardsGallery.repository.CardNodeRepository;
import it.unipi.CardsGallery.repository.PokemonCardMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pokemon")
public class PokemonCardController {

    @Autowired
    PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;

    @GetMapping
    public List<PokemonCard> getPageCards(@RequestParam("page") int page) {
        //...
        return null;
    }
}
