package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.mongo.PokemonCard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pokemon")
public class PokemonCardController {

    /*@Autowired
    PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;*/

    @GetMapping
    public List<PokemonCard> getPageCards(@RequestParam("page") int page) {
        //...
        return null;
    }
}
