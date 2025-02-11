package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.statistics.MagicCostColorDTO;
import it.unipi.CardsGallery.DTO.statistics.YugiohAttributeListDTO;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
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
}
