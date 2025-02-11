package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.DTO.statistics.MagicCostColorDTO;
import it.unipi.CardsGallery.DTO.statistics.YugiohAttributeListDTO;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/yugioh/cards")
    public ResponseEntity<ResponseWrapper<List<YugiohCard>>> getYugiohCardStatistics() {
        List<YugiohCard> list = statisticsService.yugiohCardStatistics();
        String response = (list.isEmpty()) ? "No cards found" : "Statistics computed";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/yugioh/lists")
    public ResponseEntity<ResponseWrapper<List<YugiohAttributeListDTO>>> getYugiohListsStatistics() {
        List<YugiohAttributeListDTO> list = statisticsService.yugiohListsStatistics();
        String response = (list.isEmpty()) ? "No cards found" : "Statistics computed";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/magic/cards")
    public ResponseEntity<ResponseWrapper<List<MagicCostColorDTO>>> getMagicCardStatistics() {
        List<MagicCostColorDTO> list = statisticsService.magicCardStatistics();
        String response = (list.isEmpty()) ? "No cards found" : "Statistics computed";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/magic/ratio")
    public String getMagicRatioStatistics() {

        return null;
    }

    @GetMapping("/pokemon/lists")
    public String getPokemonListsStatistics() {

        return null;
    }

    @GetMapping("/user/lists")
    public String getUserMostListsStatistics() {

        return null;
    }


    @GetMapping("/card/friend/react")
    public String getCardFriendReactStatistics() {

        return null;
    }

    @GetMapping("/user/active")
    public String getMostActiveUserStatistics() {

        return null;
    }

    @GetMapping("/user/common/react")
    public String getUsersCommonReactStatistics() {

        return null;
    }
}
