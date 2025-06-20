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

import java.util.Arrays;
import java.util.Optional;

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

        switch (type) {
            case MAGIC:
            if(!magicCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Magic card not found");
            magicCardMongoRepository.deleteById(id);
            break;

            case POKEMON:
            if(!pokemonCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Pokemon card not found");
            pokemonCardMongoRepository.deleteById(id);
            break;

            case YUGIOH:
            if(!yugiohCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Yugioh card not found");
            yugiohCardMongoRepository.deleteById(id);
            break;

            default:
            throw new NoAdminException("Type not supported");
        }
        cardListRepository.removeCardFromAllCardList(id, type);

        CardNode cardNode = new CardNode();
        cardNode.setIdentifier(id);
        cardNode.setType(type);
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE_CARD, cardNode));
    }

    @Override
    public void insertMagicCard(MagicCardDTO newMagicCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = newMagicCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        newMagicCardDTO.getMagic().setId(null);
        newMagicCardDTO.getMagic().defaultCounts();
        MagicCard magicCard = magicCardMongoRepository.save(newMagicCardDTO.getMagic());

        CardNode cardNode = new CardNode();
        cardNode.setType(TCG.MAGIC);
        cardNode.setName(newMagicCardDTO.getMagic().getName());
        cardNode.setIdentifier(magicCard.getId());

        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE_CARD, cardNode));
    }

    @Override
    public void insertPokemonCard(PokemonCardDTO newPokemonCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = newPokemonCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        newPokemonCardDTO.getPokemon().setId(null);
        newPokemonCardDTO.getPokemon().defaultCounts();
        PokemonCard pokemonCard = pokemonCardMongoRepository.save(newPokemonCardDTO.getPokemon());

        CardNode cardNode = new CardNode();
        cardNode.setType(TCG.POKEMON);
        cardNode.setName(newPokemonCardDTO.getPokemon().getName());
        cardNode.setIdentifier(pokemonCard.getId());

        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE_CARD, cardNode));
    }

    @Override
    public void insertYugiohCard(YugiohCardDTO newYugiohCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = newYugiohCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        newYugiohCardDTO.getYugioh().setId(null);
        newYugiohCardDTO.getYugioh().defaultCounts();
        YugiohCard yugiohCard = yugiohCardMongoRepository.save(newYugiohCardDTO.getYugioh());

        CardNode cardNode = new CardNode();
        cardNode.setType(TCG.YUGIOH);
        cardNode.setName(newYugiohCardDTO.getYugioh().getName());
        cardNode.setIdentifier(yugiohCard.getId());

        PendingRequests.pendingRequests.add(new Request(RequestType.CREATE_CARD, cardNode));
    }

    @Override
    public void updateMagicCard(MagicCardDTO magicCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = magicCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        String id = magicCardDTO.getMagic().getId();
        Optional<MagicCard> card = magicCardMongoRepository.findById(id);

        if(card.isEmpty()) {
            throw new ExistingEntityException("Card not found");
        }

        MagicCard magicCard = card.get();
        String newName = magicCardDTO.getMagic().getName();
        if(
            newName != null &&
            newName.equals(magicCard.getName())
        ) {
            CardNode cardNode = new CardNode();
            cardNode.setType(TCG.MAGIC);
            cardNode.setIdentifier(id);
            cardNode.setName(newName);

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE_CARD, cardNode));
        }

        if(!Arrays.equals(magicCard.getColors(), magicCardDTO.getMagic().getColors())) {
            cardListRepository.updateMgcCardInAllLists(magicCard.getId(), magicCardDTO.getMagic().getColors());
        }

        magicCard.updateCard(magicCardDTO.getMagic());
        magicCardMongoRepository.save(magicCard);
    }

    @Override
    public void updatePokemonCard(PokemonCardDTO pokemonCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = pokemonCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        String id = pokemonCardDTO.getPokemon().getId();
        Optional<PokemonCard> card = pokemonCardMongoRepository.findById(id);

        if(card.isEmpty()) {
            throw new ExistingEntityException("Card not found");
        }

        PokemonCard pokemonCard = card.get();
        String newName = pokemonCardDTO.getPokemon().getName();
        if(
                newName != null &&
                newName.equals(pokemonCard.getName())
        ) {
            CardNode cardNode = new CardNode();
            cardNode.setType(TCG.POKEMON);
            cardNode.setIdentifier(id);
            cardNode.setName(newName);

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE_CARD, cardNode));
        }

        if(!Arrays.equals(pokemonCard.getNationalPokedexNumbers(), pokemonCardDTO.getPokemon().getNationalPokedexNumbers())) {
            cardListRepository.updatePkmCardInAllLists(pokemonCard.getId(), pokemonCardDTO.getPokemon().getNationalPokedexNumbers());
        }

        pokemonCard.updateCard(pokemonCardDTO.getPokemon());
        pokemonCardMongoRepository.save(pokemonCard);
    }

    @Override
    public void updateYugiohCard(YugiohCardDTO yugiohCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = yugiohCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);

        String id = yugiohCardDTO.getYugioh().getId();
        Optional<YugiohCard> card = yugiohCardMongoRepository.findById(id);

        if(card.isEmpty()) {
            throw new ExistingEntityException("Card not found");
        }

        YugiohCard yugiohCard = card.get();
        String newName = yugiohCardDTO.getYugioh().getName();
        if(
                newName != null &&
                newName.equals(yugiohCard.getName())
        ) {
            CardNode cardNode = new CardNode();
            cardNode.setType(TCG.YUGIOH);
            cardNode.setIdentifier(id);
            cardNode.setName(newName);

            PendingRequests.pendingRequests.add(new Request(RequestType.UPDATE_CARD, cardNode));
        }

        if(!yugiohCard.getType().equals(yugiohCardDTO.getYugioh().getType()) || !yugiohCard.getAttribute().equals(yugiohCardDTO.getYugioh().getAttribute())) {
            cardListRepository.updateYgoCardInAllLists(yugiohCard.getId(), yugiohCardDTO.getYugioh().getType(), yugiohCardDTO.getYugioh().getAttribute());
        }

        yugiohCard.updateCard(yugiohCardDTO.getYugioh());
        yugiohCardMongoRepository.save(yugiohCard);
    }

    @Override
    public void deletePost(AdminDeletePostDTO dpDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = dpDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        int deleted = userRepository.deletePostFromUser(dpDTO.getUsername(), dpDTO.getPostTitle());

        if(deleted == 0)
            throw new ExistingEntityException("Post does not exist");

        PostNode postNode = new PostNode(dpDTO.getPostTitle());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE_POST, postNode, dpDTO.getUsername()));
    }

    @Override
    public void deleteUser(AdminDeleteUserDTO dto) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = dto.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        if(userRepository.deleteByUsername(dto.getUsername()) == 0) {
            throw new ExistingEntityException("User not found");
        }
        cardListRepository.deleteAllByUsername(dto.getUsername());

        UserNode userNode = new UserNode(dto.getUsername());
        PendingRequests.pendingRequests.add(new Request(RequestType.DELETE_USER, userNode));
    }
}
