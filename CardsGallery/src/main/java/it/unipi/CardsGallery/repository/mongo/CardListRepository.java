package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.DTO.statistics.MagicColorRatioDTO;
import it.unipi.CardsGallery.DTO.statistics.PokemonFirstGenDTO;
import it.unipi.CardsGallery.DTO.statistics.UserMostListsDTO;
import it.unipi.CardsGallery.DTO.statistics.YugiohAttributeListDTO;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.mongo.Card;
import it.unipi.CardsGallery.model.mongo.CardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardListRepository extends MongoRepository<CardList, String> {

    //@Query(" {'id': ?0, 'userId': ?1} ")
    boolean existsByIdAndUserId(String id, String userId);

    //@Query("{ 'id': ?0, 'cards.id': ?1 'cards.tcg': ?2 }")
    boolean existsByIdAndCardsIdAndCardsTcg(String id, String cardsId, TCG tcg);

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
    @Update("{ '$pull': { 'cards': {'id': ?1, 'tcg': ?2} } }")
    int removeCardFromCardList(String cardListId, String cardId, TCG tcg);

    @Query("{}")
    @Update("{ '$pull': { 'cards': {'id': ?0, 'tcg': ?1} } }")
    void removeCardFromAllCardList(String cardId, TCG type);

    void deleteAllByUserId(String userId);

    @Query("{ 'username': ?0 }")
    @Update("{ '$set': { 'username': ?1} }")
    void updateUsername(String oldUsername, String newUsername);

    void deleteAllByUsername(String username);

    @Aggregation(pipeline = {
            "{ '$match': { 'status': true } }",
            "{ '$unwind': '$cards' }",
            "{ '$match': { 'cards.attribute': { '$exists': true, '$ne': null } } }",
            "{ '$group': { '_id': '$cards.attribute', 'count': { '$sum': 1 } } }",
            "{ '$sort': { 'count': -1 } }",
            "{ '$project': { '_id': 0, 'attribute': '$_id', 'count': '$count' } }"
    })
    List<YugiohAttributeListDTO> getYugiohlistsStatistics();

    @Aggregation(pipeline = {
            "{'$match': {'status': true} }",
            "{ $unwind: '$cards' }",
            "{'$match': { 'cards.colors': { '$exists': true, '$ne': [], '$ne': null}}}",
            "{ $group: { " +
                    "    _id: { $cond: { if: { $eq: [ { $size: '$cards.colors' }, 1 ] }, then: 'monocolor', else: 'multicolor' } }, " +
                    "    count: { $sum: 1 } " +
                    "} }",
            "{ $group: { " +
                    "    _id: null, " +
                    "    monocolor: { $sum: { $cond: [ { $eq: ['$_id', 'monocolor'] }, '$count', 0 ] } }, " +
                    "    multicolor: { $sum: { $cond: [ { $eq: ['$_id', 'multicolor'] }, '$count', 0 ] } } " +
                    "} }",
            "{ $project: { " +
                    "    _id: 0, " +
                    "    monocolor: 1, " +
                    "    multicolor: 1, " +
                    "    ratio: { " +
                    "        $cond: { " +
                    "            if: { $eq: ['$multicolor', 0] }, " +
                    "            then: null, " +
                    "            else: { $divide: ['$monocolor', '$multicolor'] } " +
                    "        } " +
                    "    } " +
                    "} }"
    })
    MagicColorRatioDTO getMagicRatioStatistics();

    @Aggregation(pipeline = {
            "{'$match': {'status': true} }",
            "{ $unwind: '$cards' }",
            "{ $match: { 'cards.tcg': 'POKEMON', 'cards.pokedexNumber': { $elemMatch: { $gte: 0, $lte: 151 } } } }",
            "{ $group: { _id: '$cards.printingSet', count: { $sum: 1 } } }",
            "{ $project: { _id: 0, set: '$_id', count: 1 } }"
    })
    List<PokemonFirstGenDTO> getPokemonListsStatistics();

    @Aggregation(pipeline = {
            "{ $match: { 'status': true } }",
            "{ $group: { _id: '$username', lists: { $sum: 1 } } }",
            "{ $sort: { lists: -1 } }",
            "{ $limit: 1 }",
            "{ $project: { _id: 0, username: '$_id', lists: 1 } }"
    })
    UserMostListsDTO getUserMostListsStatistics();
}
