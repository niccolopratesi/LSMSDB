package it.unipi.CardsGallery;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;

public class MongoDb {

    public static void connect() {
        MongoClient myClient = MongoClients.create("mongodb://myUsedAdmin:admin@localhost:27017");
    }
}
