package it.unipi.CardsGallery.repository;

import it.unipi.CardsGallery.model.CardNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CardNodeRepository  extends Neo4jRepository<CardNode,String> {

}
