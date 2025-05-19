package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.service.YugiohCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yugioh")
public class YugiohCardController {

    @Autowired
    YugiohCardService cardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<YugiohCard>>> getYugiohCardByParameters(
            @RequestParam(required = false, defaultValue = ".*") String name,
            @RequestParam(required = false, defaultValue = ".*") String attribute,
            @RequestParam(required = false, defaultValue = ".*") String race,
            @RequestParam(required = false, defaultValue = ".*") String printing,
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        List<YugiohCard> result = cardService.getYugiohCardByParameters(name, attribute, race, printing, page);
        String msg;
        if(result == null){
            msg = "search failed";
        } else {
            msg = result.isEmpty() ? "search has no results" : "search completed successfully";
        }
        ResponseWrapper<List<YugiohCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<YugiohCard>>> getPageCards(@RequestParam("page") int page) {
        List<YugiohCard> result = cardService.getYugiohCardPage(page);
        String msg = result.isEmpty() ? "search has no results" : "search completed successfully";
        ResponseWrapper<List<YugiohCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
