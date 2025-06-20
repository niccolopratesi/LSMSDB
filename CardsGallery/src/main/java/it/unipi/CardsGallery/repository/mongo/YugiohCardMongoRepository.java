package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.YugiohCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface YugiohCardMongoRepository extends MongoRepository<YugiohCard,String> {

    Page<YugiohCard> findAll(Pageable pageable);

    @Query(
            "{" +
                    "'name': { '$regex': ?0 }, " +
                    "'attribute': { '$regex': ?1 }, " +
                    "'race': { '$regex': ?2 }, " +
                    "'printings': { '$elemMatch': { '$regex': ?3 } }" +
                    "}"
    )
    Page<YugiohCard> findByParameters(
            String name,
            String attribute,
            String race,
            String printing,
            Pageable pageable
    );

    @Query("{ 'id': ?0 }")
    Optional<YugiohCard> findById(String id);

    Boolean existsCardById(String id);

    @Query("{ 'id': ?0 }")
    @Update("{ '$inc': { 'likeCount' : ?1, 'dislikeCount': ?2, 'loveCount': ?3, 'laughCount': ?4 } }")
    void updateReactions(String cardId, int likeCount, int dislikeCount, int loveCount, int laughCount);

    @Aggregation({
            "{ '$sort': { 'atk': -1 } }",
            "{ '$group': { '_id': {level: '$level', type: '$type'}, 'card': { '$first': '$$ROOT' } } }",
            "{ '$project': { 'card': 1, '_id': 0 } }",
            "{ '$replaceRoot': { 'newRoot': '$card' } }"
    })
    List<YugiohCard> getYugiohCardStatistics();
}
