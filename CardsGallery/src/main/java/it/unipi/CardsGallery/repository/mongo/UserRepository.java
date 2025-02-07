package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("{'username': ?0}")
    public Optional<User> findUserByUsername(String username);
}
