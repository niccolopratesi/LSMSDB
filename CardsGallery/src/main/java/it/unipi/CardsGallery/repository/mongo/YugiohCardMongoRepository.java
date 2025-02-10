package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

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
    //@Update("{ '$inc': { 'likeCount' : ?1, 'dislikeCount': ?2, 'loveCount': ?3, 'laughCount': ?4 } }")
    void updateReactions(String cardId, int likeCount, int dislikeCount, int loveCount, int laughCount);

    boolean existsByCardId(String cardId);
}
