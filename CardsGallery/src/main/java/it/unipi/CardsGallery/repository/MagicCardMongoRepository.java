package it.unipi.CardsGallery.repository;

import it.unipi.CardsGallery.model.MagicCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MagicCardMongoRepository extends MongoRepository<MagicCard,String>  {
}
