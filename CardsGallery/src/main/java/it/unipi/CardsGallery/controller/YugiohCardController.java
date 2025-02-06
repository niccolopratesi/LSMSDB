package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.CommonConstants;
import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.service.YugiohCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Yugioh")
public class YugiohCardController {

    @Autowired
    YugiohCardService cardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<YugiohCard>>> getYugiohCardByParameters(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> attribute,
            @RequestParam(required = false) Optional<String> race,
            @RequestParam(required = false) Optional<String> printing,
            @RequestParam(required = true) int page
    ) {
        List<YugiohCard> result = cardService.getYugiohCardByParameters(name, attribute, race, printing, page);
        String msg;
        if(result == null){
            msg = CommonConstants.SEARCH_ERROR_MSG;
        } else {
            msg = result.isEmpty() ? CommonConstants.PAGE_EMPTY_MSG : CommonConstants.PAGE_OK_MSG;
        }
        ResponseWrapper<List<YugiohCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<YugiohCard>>> getPageCards(@RequestParam("page") int page) {
        List<YugiohCard> result = cardService.getYugiohCardPage(page);
        String msg = result.isEmpty() ? CommonConstants.PAGE_EMPTY_MSG : CommonConstants.PAGE_OK_MSG;
        ResponseWrapper<List<YugiohCard>> response = new ResponseWrapper<>(msg, result);
        return ResponseEntity.ok(response);
    }
}
