package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.CommonConstants;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.service.CardService;
import it.unipi.CardsGallery.service.MagicCardService;
import org.apache.catalina.connector.Response;
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
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> type,
            @RequestParam(required = false) Optional<String> firstPrinting,
            @RequestParam(required = false) Optional<String> manaCost,
            @RequestParam(defaultValue = "0") int page
    ) {
        List<MagicCard> result = cardService.getMagicCardByParameters(name, type, firstPrinting, manaCost, page);
        String msg;
        if(result == null){
            msg = CommonConstants.SEARCH_ERROR_MSG;
        } else {
            msg = result.isEmpty() ? CommonConstants.PAGE_EMPTY_MSG : CommonConstants.PAGE_OK_MSG;
        }
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MagicCard>>> getPageCards(@RequestParam("page") int page) {
        List<MagicCard> result = cardService.getMagicCardPage(page);
        String msg = result.isEmpty() ? CommonConstants.PAGE_EMPTY_MSG : CommonConstants.PAGE_OK_MSG;
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
