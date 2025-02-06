package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.CardNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNodeRepository  extends Neo4jRepository<CardNode,String> {
}
