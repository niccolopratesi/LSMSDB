package it.unipi.CardsGallery.repository;

import it.unipi.CardsGallery.model.YugiohCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface YugiohCardMongoRepository extends MongoRepository<YugiohCard,String> {
}
