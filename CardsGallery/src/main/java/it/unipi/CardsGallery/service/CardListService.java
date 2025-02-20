package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;

import java.util.List;

public interface CardListService {
    String createCardList(CardListDTO list) throws AuthenticationException, ExistingEntityException;
    void updateCardList(UpdateCardListDTO list) throws AuthenticationException;
    void deleteCardList(DeleteCardListDTO list) throws AuthenticationException;
    List<CardList> userCardList(String owner, int page, String username, String password) throws AuthenticationException;
    void insertIntoCardList(CardDTO card) throws AuthenticationException, ExistingEntityException;
    void removeFromCardList(CardDTO card) throws AuthenticationException, ExistingEntityException;
    List<CardList> searchCardList(String cardListName, int page);
}
