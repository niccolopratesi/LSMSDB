package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.CardListDTO;
import it.unipi.CardsGallery.DTO.UpdateCardListDTO;
import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.repository.mongo.CardListRepository;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.CardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardListServiceImpl implements CardListService {

    private CardListRepository cardListRepository;

    @Autowired
    AuthenticationService auth;

    public CardListServiceImpl() {}

    @Override
    public void createCardList(CardListDTO list) throws Exception {
        auth.authenticate(list.getAuth());
        CardList cardList = new CardList(list.getCardListName(),list.isStatus(),new ArrayList<>());
        cardListRepository.save(cardList);
    }

    @Override
    public CardList getCardList() {

        return null;
    }

    @Override
    public void updateCardList(UpdateCardListDTO list) throws Exception {
        auth.authenticate(list.getAuth());
        cardListRepository.updateCardListStatus(list.getCardListId(),list.isStatus());
    }
}
