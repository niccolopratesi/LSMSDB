package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode,String> {
}
