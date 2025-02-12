package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.statistics.*;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.neo4j.CardNode;

import java.util.List;

public interface StatisticsService {
    public List<YugiohCard> yugiohCardStatistics();
    public List<YugiohAttributeListDTO> yugiohListsStatistics();
    public PokemonFirstGenDTO pokemonListsStatistics();
    public List<MagicCostColorDTO> magicCardStatistics();
    public MagicColorRatioDTO magicRatioStatistics();
    public UserMostListsDTO userMostListsStatistics();
    public UserMostPostsDTO userMostPostsStatistics();
    public List<CardNode> cardFriendReactStatistics(String username);
    public List<MostActiveUsersDTO> mostActiveUsersStatistics(String username);
    public List<String> usersCommonReactStatistics(String username);
}
