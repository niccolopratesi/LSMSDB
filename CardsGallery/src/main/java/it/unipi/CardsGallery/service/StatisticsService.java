package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.statistics.*;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.neo4j.CardNode;

import java.util.List;

public interface StatisticsService {
    List<YugiohCard> yugiohCardStatistics();
    List<YugiohAttributeListDTO> yugiohListsStatistics();
    List<PokemonFirstGenDTO> pokemonListsStatistics();
    List<MagicCostColorDTO> magicCardStatistics();
    MagicColorRatioDTO magicRatioStatistics();
    UserMostListsDTO userMostListsStatistics();
    UserMostPostsDTO userMostPostsStatistics();
    List<CardNode> cardFriendReactStatistics(String username);
    List<MostActiveUsersDTO> mostActiveUsersStatistics(String username);
    List<String> usersCommonReactStatistics(String username);
}
