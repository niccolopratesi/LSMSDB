package it.unipi.CardsGallery;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class Neo4j {
    public static void connect() {
        //Driver driver = GraphDatabase.driver("neo4j://CardsGallery:7687");
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("","admin!23"));
    }
}
