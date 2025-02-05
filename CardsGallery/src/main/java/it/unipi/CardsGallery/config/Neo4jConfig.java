package it.unipi.CardsGallery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = "it.unipi.CardsGallery.repository.neo4j")
public class Neo4jConfig {

}
