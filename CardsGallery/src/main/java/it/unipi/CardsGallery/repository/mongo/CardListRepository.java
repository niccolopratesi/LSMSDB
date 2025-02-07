package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardListRepository extends MongoRepository<CardList, String> {

    //Optional<CardList> findByIdAndUserId(String id, String userId);
    boolean existsByIdAndUserId(String id, String userId);

    @Query("{'username': ?0}")
    Page<CardList> findByParameters(
            String username,
            Pageable pageable
    );

    @Query("{'name': {'$regex' : ?0} }")
    Page<CardList> findByName(
            String cardListName,
            Pageable pageable
    );

    //boolean existsByIdAndCardsId(String id, String cardsId);
}
