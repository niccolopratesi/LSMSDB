package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
