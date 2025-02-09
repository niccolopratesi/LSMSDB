package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.neo4j.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode,Long> {

    @Query("MATCH (userNode:User) WHERE userNode.username = $username RETURN userNode { .id, .username, .version, __nodeLabels__: labels(userNode)}")
    List<UserNode> findByUsername(String username);
}
