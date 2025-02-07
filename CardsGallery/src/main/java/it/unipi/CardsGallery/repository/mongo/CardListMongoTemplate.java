package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CardListMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public void updateCardListStatus(String id, boolean status){
        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update().set("status", status);

        mongoTemplate.updateFirst(query, update, CardList.class);
    }

    public void insertCardIntoCardList(String cardListId, Card card){
        Query query = new Query(Criteria.where("id").is(cardListId));
        Update update = new Update().addToSet("cards", card);
        mongoTemplate.updateFirst(query, update, CardList.class);
    }

    public void removeCardFromCardList(String cardListId, String cardId){
        Query query = new Query(Criteria.where("id").is(cardListId));
        Update update = new Update().pull("cards", new Query(Criteria.where("id").is(cardId)));
        mongoTemplate.updateFirst(query, update, CardList.class);
    }
}
