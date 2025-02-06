package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.MagicCard;
import it.unipi.CardsGallery.repository.neo4j.CardNodeRepository;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Magic")
public class MagicCardController {

    @Autowired
    MagicCardMongoRepository magicCardMongoRepository;

    @GetMapping
    public List<MagicCard> getPageCards(@RequestParam("page") int page) {

        return null;
    }
    /*
    @GetMapping
    public MagicCard getPageCards(@RequestParam("page") int page) {
        MagicCard magicCard = magicCardMongoRepository.findByName("A Girl and Her Dogs");
        return magicCard;
    }*/
}
