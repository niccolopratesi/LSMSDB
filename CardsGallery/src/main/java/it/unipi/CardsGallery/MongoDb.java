package it.unipi.CardsGallery;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;

public class MongoDb {

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbURI;

    @Value("${spring.data.mongodb.username}")
    private String mongoDbUsername;

    @Value("${spring.data.mongodb.password}")
    private String mongoDbPassword;

    public static void connect() {
        MongoClient myClient = MongoClients.create("mongodb://myUserAdmin:admin@localhost:27017");
    }
}
