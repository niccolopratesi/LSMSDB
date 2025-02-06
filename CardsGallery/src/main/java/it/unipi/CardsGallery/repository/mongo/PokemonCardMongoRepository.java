package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.PokemonCard;
import it.unipi.CardsGallery.model.mongo.PokemonCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardMongoRepository extends MongoRepository<PokemonCard,String> {
    Page<PokemonCard> findAll(Pageable pageable);

    @Query(
            "{" +
                    "'name': { '$regex': ?0 }, " +
                    "'rarity': { '$regex': ?1 }, " +
                    "'set': { '$regex': ?2 }, " +
                    "'artist': { '$regex': ?3 }" +
                    "}"
    )
    Page<PokemonCard> findByParameters(
            String name,
            String rarity,
            String set,
            String artist,
            Pageable pageable
    );
}
