package it.unipi.CardsGallery.repository;

import it.unipi.CardsGallery.model.PokemonCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonCardMongoRepository extends MongoRepository<PokemonCard,String> {

}
