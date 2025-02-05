package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.MagicCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardMongoRepository extends MongoRepository<MagicCard,String>  {
}
