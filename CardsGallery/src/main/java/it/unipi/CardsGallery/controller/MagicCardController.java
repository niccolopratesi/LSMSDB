package it.unipi.CardsGallery.controller;

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
    final private String EMPTY_PAGE_MSG = "empty page";
    final private String PAGE_OK_MSG = "page retrieved successfully";

    @Autowired
    MagicCardService cardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<MagicCard>>> getMagicCardByParameters(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> type,
            @RequestParam(required = false) Optional<String> firstPrinting,
            @RequestParam(required = false) Optional<String> manaCost,
            @RequestParam(required = true) int page
    ) {
        List<MagicCard> result = cardService.getMagicCardByParameters(name, type, firstPrinting, manaCost, page);
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>("search completed successfully", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MagicCard>>> getPageCards(@RequestParam("page") int page) {
        List<MagicCard> result = cardService.getMagicCardPage(page);
        String msg = result.isEmpty() ? EMPTY_PAGE_MSG : PAGE_OK_MSG;
        ResponseWrapper<List<MagicCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
