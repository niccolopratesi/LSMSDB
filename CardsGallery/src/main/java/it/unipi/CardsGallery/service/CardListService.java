package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;

import java.util.List;

public interface CardListService {
    public void createCardList(CardListDTO list) throws AuthenticationException;
    public void updateCardList(UpdateCardListDTO list) throws AuthenticationException;
    public void deleteCardList(DeleteCardListDTO list) throws AuthenticationException;
    public List<CardList> userCardList(String owner, int page, String username, String password) throws AuthenticationException;
    public void insertIntoCardList(CardDTO card) throws AuthenticationException, ExistingEntityException;
    public void removeFromCardList(DeleteCardDTO card) throws AuthenticationException;
    public List<CardList> searchCardList(String cardListName, int page);
}
