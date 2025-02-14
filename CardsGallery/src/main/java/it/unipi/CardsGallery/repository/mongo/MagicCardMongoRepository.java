package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.DTO.statistics.MagicCostColorDTO;
import it.unipi.CardsGallery.model.mongo.MagicCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagicCardMongoRepository extends MongoRepository<MagicCard,String>  {

    Page<MagicCard> findAll(Pageable pageable);

    @Query(
            "{" +
                "'name': { '$regex': ?0 }, " +
                "'type': { '$regex': ?1 }, " +
                "'firstPrinting': { '$regex': ?2 }, " +
                "'manaCost': { '$regex': ?3 }" +
            "}"
    )
    Page<MagicCard> findByParameters(
            String name,
            String type,
            String firstPrinting,
            String manaCost,
            Pageable pageable
    );

    @Query("{ 'id': ?0 }")
    @Update("{ '$inc': { 'likeCount' : ?1, 'dislikeCount': ?2, 'loveCount': ?3, 'laughCount': ?4 } }")
    void updateReactions(String cardId, int likeCount, int dislikeCount, int loveCount, int laughCount);

    @Aggregation(pipeline = {
            "{ '$unwind': '$colors' }",
            "{ '$group': { '_id': '$colors', 'avgManaValue': { '$avg': '$manaValue' } } }",
            "{ '$project': { '_id': 0, 'color': '$_id', 'avgManaValue': 1 } }"
    })
    List<MagicCostColorDTO> getMagicCardStatistics();
}
