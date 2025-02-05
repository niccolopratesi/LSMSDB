package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.PokemonCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardMongoRepository extends MongoRepository<PokemonCard,String> {
}
