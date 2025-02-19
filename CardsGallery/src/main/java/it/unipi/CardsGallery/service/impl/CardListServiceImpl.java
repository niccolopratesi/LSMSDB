package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.*;
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
import java.util.Optional;

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
    public String createCardList(CardListDTO list) throws AuthenticationException, ExistingEntityException {
        if(list.getCardListName() == null || list.getCardListName().trim().equals("")) {
            throw new ExistingEntityException("Please enter card list name");
        }
        auth.accountOwnership(list.getAuth());
        CardList cardList = new CardList(list.getCardListName(),list.isStatus(),new ArrayList<>(),list.getAuth().getId(),list.getAuth().getUsername());
        cardList.setId(null);
        return cardListRepository.save(cardList).getId();
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
        auth.accountOwnership(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());
        if(cardListRepository.existsByIdAndCardsIdAndCardsTcg(card.getCardListId(), card.getCardId(), card.getTcg())) {
            throw new ExistingEntityException("Card already in the card list");
        }

        Card c = new Card();
        c.setId(card.getCardId());
        c.setTcg(card.getTcg());
        boolean exist = false;

        switch (card.getTcg()) {
            case MAGIC:
                MagicCard mc = magicCardMongoRepository.findById(card.getCardId()).orElse(null);
                if(mc != null) {
                    exist = true;
                    c.setPrintingSet(mc.getFirstPrinting());
                    c.setColors(List.of(mc.getColors()));
                    c.setName(mc.getName());
                }
                break;
            case POKEMON:
                PokemonCard pc = pokemonCardMongoRepository.findById(card.getCardId()).orElse(null);
                if(pc != null) {
                    exist = true;
                    c.setPrintingSet(pc.getSet());
                    c.setPokedexNumber(pc.getNationalPokedexNumbers());
                    c.setName(pc.getName());
                }
                break;
            case YUGIOH:
                YugiohCard yc = yugiohCardMongoRepository.findById(card.getCardId()).orElse(null);
                if(yc != null) {
                    exist = true;
                    c.setPrintingSet(yc.getPrintings().length != 0 ? yc.getPrintings()[0] : null);
                    c.setAttribute(yc.getAttribute());
                    c.setType(yc.getType());
                    c.setName(yc.getName());
                }
                break;
            default:
                throw new ExistingEntityException("Please enter card's Tcg correctly");
        }

        if (!exist) {
            throw new ExistingEntityException("Card does not exist");
        }

        cardListRepository.insertCardIntoCardList(card.getCardListId(), c);
    }

    @Override
    public void removeFromCardList(CardDTO card) throws AuthenticationException {
        auth.authenticate(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());
        cardListRepository.removeCardFromCardList(card.getCardListId(),card.getCardId(), card.getTcg());
    }
}
