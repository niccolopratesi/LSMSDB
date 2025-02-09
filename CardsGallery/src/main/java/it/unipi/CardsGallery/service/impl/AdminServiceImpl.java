package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.RequestType;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.model.mongo.PokemonCard;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.PendingRequests;
import it.unipi.CardsGallery.pendingRequests.Request;
import it.unipi.CardsGallery.repository.mongo.*;
import it.unipi.CardsGallery.service.AdminService;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import it.unipi.CardsGallery.model.enums.TCG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;


    @Override
    public void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardNoFieldsDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardNoFieldsDTO.getType();
        String id = adminCardNoFieldsDTO.getId();

        CardNode cardNode = new CardNode();
        cardNode.setIdentifier(id);

        switch (type) {
            case MAGIC:
            if(!magicCardMongoRepository.existsById(id))
                throw new ExistingEntityException("magic card not found");
            magicCardMongoRepository.deleteById(id);
            break;

            case POKEMON:
            if(!pokemonCardMongoRepository.existsById(id))
                throw new ExistingEntityException("pokemon card not found");
            pokemonCardMongoRepository.deleteById(id);
            break;

            case YUGIOH:
            if(!yugiohCardMongoRepository.existsById(id))
                throw new ExistingEntityException("yugioh card not found");
            yugiohCardMongoRepository.deleteById(id);
            break;

            default:
            throw new NoAdminException("Type not supported");
        }
        cardListRepository.removeCardFromAllCardList(id, type);

        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, cardNode));
    }

    @Override
    public void updateCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardDTO.getType();
        String id;

        CardNode cardNode = new CardNode();

        switch (type) {
            case MAGIC:
            id = adminCardDTO.getMagic().getId();
            cardNode.setIdentifier(id);
            cardNode.setType(TCG.MAGIC);
            cardNode.setName(adminCardDTO.getMagic().getName());
            if(!magicCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            magicCardMongoRepository.save(adminCardDTO.getMagic());
            break;

            case POKEMON:
            id = adminCardDTO.getPokemon().getId();
            cardNode.setIdentifier(id);
            cardNode.setType(TCG.POKEMON);
            cardNode.setName(adminCardDTO.getPokemon().getName());
            if(!pokemonCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            pokemonCardMongoRepository.save(adminCardDTO.getPokemon());
            break;

            case YUGIOH:
            id = adminCardDTO.getYugioh().getId();
            cardNode.setIdentifier(id);
            cardNode.setType(TCG.YUGIOH);
            cardNode.setName(adminCardDTO.getYugioh().getName());
            if(!yugiohCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            yugiohCardMongoRepository.save(adminCardDTO.getYugioh());
            break;

            default:
                throw new ExistingEntityException("Invalid TCG type");
        }

        PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE, cardNode));
    }

    @Override
    public void insertCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardDTO.getType();

        CardNode cardNode = new CardNode();

        switch (type) {
            case MAGIC:
            adminCardDTO.getMagic().setId(null);
            MagicCard magicCard = magicCardMongoRepository.save(adminCardDTO.getMagic());
            cardNode.setName(adminCardDTO.getMagic().getName());
            cardNode.setType(TCG.MAGIC);
            cardNode.setIdentifier(magicCard.getId());
            break;

            case POKEMON:
            adminCardDTO.getPokemon().setId(null);
            PokemonCard pokemonCard = pokemonCardMongoRepository.save(adminCardDTO.getPokemon());
            cardNode.setName(adminCardDTO.getYugioh().getName());
            cardNode.setType(TCG.YUGIOH);
            cardNode.setIdentifier(pokemonCard.getId());
            break;

            case YUGIOH:
            adminCardDTO.getYugioh().setId(null);
            YugiohCard yugiohCard = yugiohCardMongoRepository.save(adminCardDTO.getYugioh());
            cardNode.setName(adminCardDTO.getPokemon().getName());
            cardNode.setType(TCG.POKEMON);
            cardNode.setIdentifier(yugiohCard.getId());
            break;

            default:
            throw new ExistingEntityException("Invalid TCG type");
        }

        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE, cardNode));
    }

    @Override
    public void deletePost(AdminDeletePostDTO dpDTO) throws AuthenticationException, NoAdminException {
        AuthDTO authDTO = dpDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        userRepository.deletePostFromUser(dpDTO.getUsername(), dpDTO.getPostTitle());

        PostNode postNode = new PostNode(dpDTO.getPostTitle());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, postNode, dpDTO.getUsername()));
    }

    @Override
    public void deleteUser(UserDTO dto) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = dto.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        if(!userRepository.existsUserByUsername(dto.getUsername()))
            throw new ExistingEntityException("User not found");
        userRepository.deleteByUsername(dto.getUsername());
        cardListRepository.deleteAllByUsername(dto.getUsername());

        UserNode userNode = new UserNode(dto.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE, userNode));
    }
}
