package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode,Long> {

    @Query(
            "MATCH (u:User) WHERE u.username = $username " +
            "CREATE (p:Post {title: $title}) " +
            "CREATE (u)-[:CREATED]->(p)"
    )
    void createPost(String username, String title);

    @Query("MATCH (u:User)-[:CREATED]->(p:Post)" +
           "WHERE u.username = $username AND p.title = $title" +
           "DETACH DELETE p")
    void delete(String username, String title);

    //@Query("")
    void react(String username, String title, String postOwner, Reaction reaction);

    //@Query("")
    void reactDelete(String username, String title, String postOwner, Reaction reaction);
}
