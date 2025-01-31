package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.PokemonCard;
import it.unipi.CardsGallery.repository.PokemonCardMongoRepository;
import it.unipi.CardsGallery.repository.PokemonCardNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Pokemon")
public class PokemonCardController {

    @Autowired
    PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    PokemonCardNeo4jRepository pokemonCardNeo4JRepository;

    @GetMapping("/{page}")
    public List<PokemonCard> getPageCards(@PathVariable int page) {
        //...
        return null;
    }
}
