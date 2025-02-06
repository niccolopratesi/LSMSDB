package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
