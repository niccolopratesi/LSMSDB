package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.MagicCard;
import it.unipi.CardsGallery.repository.CardNodeRepository;
import it.unipi.CardsGallery.repository.MagicCardMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Magic")
public class MagicCardController {
    @Autowired
    MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;

    @GetMapping("/{page}")
    public List<MagicCard> getPageCards(@PathVariable int page) {
        //...
        return null;
    }
}
