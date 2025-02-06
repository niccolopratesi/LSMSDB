package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.model.mongo.YugiohCard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Yugioh")
public class YugiohCardController {

    /*@Autowired
    YugiohCardMongoRepository yugiohCardMongoRepository;

    @Autowired
    CardNodeRepository cardNodeRepository;*/

    @GetMapping
    public List<YugiohCard> getPageCards(@RequestParam("page") int page) {
        //...
        return null;
    }
}
