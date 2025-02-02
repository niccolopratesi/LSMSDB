package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.MagicCard;
import it.unipi.CardsGallery.repository.CardNodeRepository;
import it.unipi.CardsGallery.repository.MagicCardMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Magic")
public class MagicCardController {
    @Autowired
    MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;

    @GetMapping
    public List<MagicCard> getPageCards(@RequestParam("page") int page) {
        //...
        return null;
    }
}
