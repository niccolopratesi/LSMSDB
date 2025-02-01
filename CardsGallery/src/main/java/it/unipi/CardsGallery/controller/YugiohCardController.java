package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.YugiohCard;
import it.unipi.CardsGallery.repository.CardNodeRepository;
import it.unipi.CardsGallery.repository.YugiohCardMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Yugioh")
public class YugiohCardController {

    @Autowired
    YugiohCardMongoRepository yugiohCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;

    @GetMapping("/{page}")
    public List<YugiohCard> getPageCards(@PathVariable int page) {
        //...
        return null;
    }
}
