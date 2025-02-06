package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.CardListDTO;
import it.unipi.CardsGallery.DTO.UpdateCardListDTO;
import it.unipi.CardsGallery.model.mongo.CardList;

public interface CardListService {
    public CardList getCardList();
    public void createCardList(CardListDTO list) throws Exception;
    public void updateCardList(UpdateCardListDTO list) throws Exception;
}
