package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.PokemonCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.YugiohCardMongoRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardListServiceImpl implements CardListService {

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private AuthenticationService auth;

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;

    public CardListServiceImpl() {}

    @Override
    public void createCardList(CardListDTO list) throws AuthenticationException, ExistingEntityException {
        if(list.getCardListName() == null || list.getCardListName().trim().equals("")) {
            throw new ExistingEntityException("Please enter card list name");
        }
        auth.accountOwnership(list.getAuth());
        CardList cardList = new CardList(list.getCardListName(),list.isStatus(),new ArrayList<>(),list.getAuth().getId(),list.getAuth().getUsername());
        cardList.setId(null);
        cardListRepository.save(cardList);
    }

    @Override
    public void updateCardList(UpdateCardListDTO list) throws AuthenticationException {
        auth.authenticate(list.getAuth());
        auth.listOwnership(list.getAuth().getId(), list.getCardListId());
        cardListRepository.updateCardListStatus(list.getCardListId(),list.isStatus());
    }

    @Override
    public void deleteCardList(DeleteCardListDTO list) throws AuthenticationException {
        auth.authenticate(list.getAuth());
        auth.listOwnership(list.getAuth().getId(), list.getCardListId());
        cardListRepository.deleteById(list.getCardListId());
    }

    @Override
    public List<CardList> searchCardList(String cardListName, int page) {
        if(page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Constants.CARDLIST_PAGE_SIZE, Sort.by("id").ascending());
        Page<CardList> result = cardListRepository.findByName(cardListName, pageable);
        return result.getContent();
    }

    @Override
    public List<CardList> userCardList(String owner, int page, String username, String password) throws AuthenticationException {
        if(page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Constants.CARDLIST_PAGE_SIZE, Sort.by("id").ascending());
        Page<CardList> result;

        if(username != null && password != null && username.equals(owner)) {
            auth.authenticate(new AuthDTO(username, password));
            result = cardListRepository.findOwnedLists(owner, pageable);
        } else {
            result = cardListRepository.findByOwner(owner, pageable);
        }
        return result.getContent();
    }

    @Override
    public void insertIntoCardList(CardDTO card) throws AuthenticationException, ExistingEntityException {
        auth.authenticate(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());
        if(cardListRepository.existsByIdAndCardsId(card.getCardListId(),card.getCard().getId())) {
            throw new ExistingEntityException("Card already in the card list");
        }

        boolean result;

        switch (card.getCard().getTcg()) {
            case MAGIC:
                result = magicCardMongoRepository.existsById(card.getCard().getId());
                card.getCard().setType(null);
                card.getCard().setAttribute(null);
                card.getCard().setPokedexNumber(null);
                break;
            case POKEMON:
                result = pokemonCardMongoRepository.existsById(card.getCard().getId());
                card.getCard().setType(null);
                card.getCard().setAttribute(null);
                card.getCard().setColors(null);
                break;
            case YUGIOH:
                result = yugiohCardMongoRepository.existsById(card.getCard().getId());
                card.getCard().setPokedexNumber(null);
                card.getCard().setColors(null);
                break;
            default:
                throw new ExistingEntityException("Please enter card's Tcg correctly");
        }

        if (!result) {
            throw new ExistingEntityException("Card does not exist");
        }

        cardListRepository.insertCardIntoCardList(card.getCardListId(), card.getCard());
    }

    @Override
    public void removeFromCardList(DeleteCardDTO card) throws AuthenticationException {
        auth.authenticate(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());
        cardListRepository.removeCardFromCardList(card.getCardListId(),card.getCardId());
    }
}
