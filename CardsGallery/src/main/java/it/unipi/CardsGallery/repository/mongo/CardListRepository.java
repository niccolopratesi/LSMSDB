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

    @Query("{'_id': ?0 }")
    void updateCardListStatus(String id, boolean status);

    Optional<CardList> findByIdAndUserId(String id, String userId);

    @Query("{'username': ?0}")
    Page<CardList> findByParameters(
            String username,
            Pageable pageable
    );

    void insertCardById(String cardListId, Card card);
    void deleteCardById(String cardListId, String cardId);
}
