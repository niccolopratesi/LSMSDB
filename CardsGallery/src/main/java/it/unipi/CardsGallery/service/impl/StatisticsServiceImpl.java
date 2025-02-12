package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.statistics.*;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.repository.mongo.*;
import it.unipi.CardsGallery.repository.neo4j.CardNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.PostNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.UserNodeRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private UserNodeRepository userNodeRepository;

    @Autowired
    private CardNodeRepository cardNodeRepository;

    @Autowired
    private PostNodeRepository postNodeRepository;

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;


    @Override
    public List<YugiohCard> yugiohCardStatistics() {
        return yugiohCardMongoRepository.getYugiohCardStatistics();
    }

    @Override
    public List<YugiohAttributeListDTO> yugiohListsStatistics() {
        return cardListRepository.getYugiohlistsStatistics();
    }

    @Override
    public List<MagicCostColorDTO> magicCardStatistics() {
        return magicCardMongoRepository.getMagicCardStatistics();
    }

    @Override
    public MagicColorRatioDTO magicRatioStatistics() {
        return cardListRepository.getMagicRatioStatistics();
    }

    public PokemonFirstGenDTO pokemonListsStatistics() {
        return cardListRepository.getPokemonListsStatistics();
    }

    @Override
    public UserMostListsDTO userMostListsStatistics() {
        return cardListRepository.getUserMostListsStatistics();
    }

    @Override
    public UserMostPostsDTO userMostPostsStatistics() {
        return userRepository.getUserMostPostsStatistics();
    }

    @Override
    public List<CardNode> cardFriendReactStatistics(String username) {
        return cardNodeRepository.getCardFriendReactStatistics(username);
    }

    @Override
    public List<MostActiveUsersDTO> mostActiveUsersStatistics(String username) {
        return userNodeRepository.getMostActiveUsersStatistics(username);
    }

    @Override
    public List<String> usersCommonReactStatistics(String username) {
        return userNodeRepository.getUsersCommonReactStatistics(username);
    }
}
