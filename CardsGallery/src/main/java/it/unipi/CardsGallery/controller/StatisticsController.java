package it.unipi.CardsGallery.controller;

import it.unipi.CardsGallery.DTO.ResponseWrapper;
import it.unipi.CardsGallery.DTO.statistics.*;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        String response = (list.isEmpty()) ? "No cards found" : "Yugioh cards with the highest atk per level and type";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/yugioh/lists")
    public ResponseEntity<ResponseWrapper<List<YugiohAttributeListDTO>>> getYugiohListsStatistics() {
        List<YugiohAttributeListDTO> list = statisticsService.yugiohListsStatistics();
        String response = (list.isEmpty()) ? "No cards found" : "Number of yugioh cards in public lists per attribute";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/magic/cards")
    public ResponseEntity<ResponseWrapper<List<MagicCostColorDTO>>> getMagicCardStatistics() {
        List<MagicCostColorDTO> list = statisticsService.magicCardStatistics();
        String response = (list.isEmpty()) ? "No cards found" : "Average mana cost per color";
        return ResponseEntity.ok(new ResponseWrapper<>(response,list));
    }

    @GetMapping("/magic/ratio")
    public ResponseEntity<ResponseWrapper<MagicColorRatioDTO>> getMagicRatioStatistics() {
        MagicColorRatioDTO result = statisticsService.magicRatioStatistics();
        return ResponseEntity.ok(new ResponseWrapper<>("Ratio between monocolored and multicolored magic cards in public lists",result));
    }

    @GetMapping("/pokemon/lists")
    public ResponseEntity<ResponseWrapper<PokemonFirstGenDTO>> getPokemonListsStatistics() {
        PokemonFirstGenDTO result = statisticsService.pokemonListsStatistics();
        return ResponseEntity.ok(new ResponseWrapper<>("Number of First Gen pokemon cards in public lists",result));
    }

    @GetMapping("/user/lists")
    public ResponseEntity<ResponseWrapper<UserMostListsDTO>> getUserMostListsStatistics() {
        UserMostListsDTO result = statisticsService.userMostListsStatistics();
        return ResponseEntity.ok(new ResponseWrapper<>("User with the most number of public lists",result));
    }

    @GetMapping("/user/posts")
    public ResponseEntity<ResponseWrapper<UserMostPostsDTO>> getUserMostPostsStatistics() {
        UserMostPostsDTO result = statisticsService.userMostPostsStatistics();
        return ResponseEntity.ok(new ResponseWrapper<>("User with the most number of posts",result));
    }

    @GetMapping("/card/friend/react")
    public ResponseEntity<ResponseWrapper<List<CardNode>>> getCardFriendReactStatistics(@RequestParam("username") String username) {
        List<CardNode> result = statisticsService.cardFriendReactStatistics(username);
        return ResponseEntity.ok(new ResponseWrapper<>("Cards with the most number of reacts from friends",result));
    }

    @GetMapping("/user/active")
    public ResponseEntity<ResponseWrapper<List<MostActiveUsersDTO>>> getMostActiveUsersStatistics(@RequestParam("username") String username) {
        List<MostActiveUsersDTO> result = statisticsService.mostActiveUsersStatistics(username);
        return ResponseEntity.ok(new ResponseWrapper<>("The most active followed users",result));
    }

    @GetMapping("/user/common/react")
    public ResponseEntity<ResponseWrapper<List<String>>> getUsersCommonReactStatistics(@RequestParam("username") String username) {
        String response = (username == null) ? ("Users with most number of react to cards in common with "+ username) : "No username input";
        List<String> result = statisticsService.usersCommonReactStatistics(username);
        return ResponseEntity.ok(new ResponseWrapper<>(response,result));
    }
}
