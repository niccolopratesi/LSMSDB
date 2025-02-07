package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardListRepository extends MongoRepository<CardList, String> {

    //Optional<CardList> findByIdAndUserId(String id, String userId);
    boolean existsByIdAndUserId(String id, String userId);

    @Query("{'username': ?0}")
    Page<CardList> findOwnedLists(
            String username,
            Pageable pageable
    );

    @Query("{'username': ?0, 'status': true }")
    Page<CardList> findByOwner(
            String username,
            Pageable pageable
    );

    @Query("{'name': {'$regex' : ?0}, 'status': true}")
    Page<CardList> findByName(
            String cardListName,
            Pageable pageable
    );

    @Query("{ 'id': ?0 }")
    @Update("{ '$set': {'status': ?1} }")
    void updateCardListStatus(String id, boolean status);

    @Query("{ 'id': ?0 }")
    @Update("{ '$push': { 'cards': ?1 } }")
    void insertCardIntoCardList(String cardListId, Card card);

    @Query("{ 'id': ?0 }")
    @Update("{ '$pull': { 'cards': {'id': ?1} } }")
    void removeCardFromCardList(String cardListId, String cardId);

    @Query("{}")
    @Update("{ '$pull': { 'cards': {'id': ?1} } }")
    void removeCardFromAllCardList(String cardId);

    //boolean existsByIdAndCardsId(String id, String cardsId);
}
