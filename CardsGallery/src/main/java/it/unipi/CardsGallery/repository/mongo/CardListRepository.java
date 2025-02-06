package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.CardList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardListRepository extends MongoRepository<CardList, String> {

    @Query("{'_id': ?0 }")
    void updateCardListStatus(String id, boolean status);
}
