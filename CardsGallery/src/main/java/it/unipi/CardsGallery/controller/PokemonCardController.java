package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.PokemonCard;
import it.unipi.CardsGallery.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Pokemon")
public class PokemonCardController {

    @Autowired
    PokemonCardService cardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<PokemonCard>>> getPokemonCardByParameters(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> rarity,
            @RequestParam(required = false) Optional<String> set,
            @RequestParam(required = false) Optional<String> artist,
            @RequestParam(defaultValue = "0") int page
    ) {
        List<PokemonCard> result = cardService.getPokemonCardByParameters(name, rarity, set, artist, page);
        String msg;
        if(result == null){
            msg = "search failed";
        } else {
            msg = result.isEmpty() ? "search has no results" : "search completed successfully";
        }
        ResponseWrapper<List<PokemonCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<PokemonCard>>> getPageCards(@RequestParam("page") int page) {
        List<PokemonCard> result = cardService.getPokemonCardPage(page);
        String msg = result.isEmpty() ? "search has no result" : "search completed successfully";
        ResponseWrapper<List<PokemonCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
