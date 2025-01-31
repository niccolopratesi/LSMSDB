package it.unipi.CardsGallery.repository;

import it.unipi.CardsGallery.model.PokemonCard;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PokemonCardNeo4jRepository extends Neo4jRepository<PokemonCard, String> {

}
