package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public User findUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        query.fields().exclude("password");
        return mongoTemplate.findOne(query, User.class);
    }
}
