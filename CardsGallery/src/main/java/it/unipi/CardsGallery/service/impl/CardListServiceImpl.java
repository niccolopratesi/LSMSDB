package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.CardListService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
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
    AuthenticationService auth;

    public CardListServiceImpl() {}

    @Override
    public void createCardList(CardListDTO list) throws AuthenticationException {
        auth.authenticate(list.getAuth());
        CardList cardList = new CardList(list.getCardListName(),list.isStatus(),new ArrayList<>(),list.getAuth().getId(),list.getAuth().getUsername());
        cardListRepository.save(cardList);
    }

    @Override
    public void updateCardList(UpdateCardListDTO list) throws AuthenticationException {
        auth.authenticate(list.getAuth());
        cardListRepository.updateCardListStatus(list.getCardListId(),list.isStatus());
    }

    @Override
    public void deleteCardList(DeleteCardListDTO list) throws AuthenticationException {
        auth.authenticate(list.getAuth());
        auth.listOwnership(list.getAuth().getId(), list.getCardListId());

        cardListRepository.deleteById(list.getCardListId());
    }

    @Override
    public List<CardList> userCardList(String username, int page, AuthDTO authdto) throws AuthenticationException {
        auth.authenticate(authdto);

        Pageable pageable = PageRequest.of(page, 3, Sort.by("id").ascending()); // Sorting optional
        Page<CardList> result = cardListRepository.findByParameters(username, pageable);
        return result.getContent();
    }

    @Override
    public void insertIntoCardList(CardDTO card) throws AuthenticationException {
        auth.authenticate(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());

        cardListRepository.insertCardById(card.getCardListId(), card.getCard());
    }

    @Override
    public void removeFromCardList(DeleteCardDTO card) throws AuthenticationException {
        auth.authenticate(card.getAuth());
        auth.listOwnership(card.getAuth().getId(), card.getCardListId());

        cardListRepository.deleteCardById(card.getCardListId(),card.getCardId());
    }
}
