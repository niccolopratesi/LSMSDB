package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.service.MagicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Magic")
public class MagicCardController {

    @Autowired
    MagicCardService cardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<MagicCard>>> getMagicCardByParameters(
            @RequestParam(required = false, defaultValue = ".*") String name,
            @RequestParam(required = false, defaultValue = ".*") String type,
            @RequestParam(required = false, defaultValue = ".*") String firstPrinting,
            @RequestParam(required = false, defaultValue = ".*") String manaCost,
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        List<MagicCard> result = cardService.getMagicCardByParameters(name, type, firstPrinting, manaCost, page);
        String msg;
        if(result == null){
            msg = "error on search";
        } else {
            msg = result.isEmpty() ? "search has no results" : "search completed successfully";
        }
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MagicCard>>> getPageCards(@RequestParam("page") int page) {
        List<MagicCard> result = cardService.getMagicCardPage(page);
        String msg = result.isEmpty() ? "search has no results" : "search completed successfully";
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
