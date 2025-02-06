package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.neo4j.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode,String> {
}
