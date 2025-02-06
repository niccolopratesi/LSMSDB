package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.service.exception.AuthenticationException;

import java.util.List;

public interface CardListService {
    public void createCardList(CardListDTO list) throws AuthenticationException;
    public void updateCardList(UpdateCardListDTO list) throws AuthenticationException;
    public void deleteCardList(DeleteCardListDTO list) throws AuthenticationException;
    public List<CardList> userCardList(String username, int page, AuthDTO auth) throws AuthenticationException;
    public void insertIntoCardList(CardDTO card) throws AuthenticationException;
    public void removeFromCardList(DeleteCardDTO card) throws AuthenticationException;
}
