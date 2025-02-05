package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.YugiohCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YugiohCardMongoRepository extends MongoRepository<YugiohCard,String> {
}
