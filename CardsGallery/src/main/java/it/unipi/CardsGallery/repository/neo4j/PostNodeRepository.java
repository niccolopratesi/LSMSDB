package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.DTO.ReactionCount;
import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode,Long> {

    @Query(
            "MATCH (u:User) WHERE u.username = $username " +
            "CREATE (p:Post {title: $title}) " +
            "CREATE (u)-[:CREATED]->(p)"
    )
    void createPost(String username, String title);

    @Query("MATCH (u:User)-[:CREATED]->(p:Post) " +
           "WHERE u.username = $username AND p.title = $title " +
           "DETACH DELETE p")
    void delete(String username, String title);

    @Query("MATCH (u:User {username: $username}), (p:Post {title: $title}), (o:User {username: $postOwner}) " +
            "WHERE EXISTS((o)-[:CREATED]->(p)) " +
            "MERGE (u)-[r:REACTED]->(p) " +
            "SET r.reaction = $reaction")
    void react(String username, String title, String postOwner, Reaction reaction);

    @Query("MATCH (u:User {username: $username}), (p:Post {title: $title}), (o:User {username: $postOwner}) " +
            "WHERE EXISTS((o)-[:CREATED]->(p)) " +
            "MATCH (u)-[r:REACTED {reaction: $reaction}]-(p) " +
            "DELETE r " +
            "RETURN COUNT(r) > 0"
    )
    boolean reactDelete(String username, String title, String postOwner, Reaction reaction);

    @Query("MATCH (p:Post)<-[r:REACTED]-(u:User), (o:User)-[:CREATED]->(p) " +
            "WHERE p.title = $title AND o.username = $owner " +
            "RETURN r.reaction AS reaction, COUNT(r) AS count")
    List<ReactionCount>  getCounts(String owner, String title);

    @Query("MATCH (p:Post)<-[r:REACTED]-(u:User), (o:User)-[:CREATED]->(p) " +
            "WHERE p.title = $title AND o.username = $owner AND u.username = $username " +
            "RETURN r.reaction")
    Reaction getReact(String username, String owner, String title);
}
