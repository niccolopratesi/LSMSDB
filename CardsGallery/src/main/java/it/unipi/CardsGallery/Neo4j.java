package it.unipi.CardsGallery;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;

public class Neo4j {

    @Value("${spring.ds_neo4j.uri }")
    private String neo4jURI;

    @Value("${spring.ds_neo4j.username}")
    private String neo4jUSername;

    @Value("${spring.ds_neo4j.password}")
    private String neo4jPassword;

    public static void connect() {
        //Driver driver = GraphDatabase.driver("neo4j://CardsGallery:7687");

        Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("","admin!23"));
    }
}
