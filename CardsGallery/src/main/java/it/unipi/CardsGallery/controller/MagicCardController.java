package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.mongo.MagicCard;
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
}
