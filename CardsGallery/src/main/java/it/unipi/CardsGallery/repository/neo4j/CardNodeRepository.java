package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNodeRepository  extends Neo4jRepository<CardNode,Long> {

    @Query("MATCH (c:Card)" +
            "WHERE c.identifier = $identifier AND c.type = $type" +
            "DETACH DELETE c")
    void delete(String identifier, TCG type);

    @Query("MATCH (c:Card)" +
           "WHERE c.identifier = $identifier AND c.type = $type" +
           "SET c.name = $name")
    void update(String identifier, TCG type, String name);

    /*
    * MERGE works like a combination of MATCH and CREATE.
    * It first looks for existing nodes or relationships that match the criteria. If they exist, it returns them.
    * If not, it creates the nodes or relationships.
     * */
    @Query("MATCH (u:User), (c:Card) " +
            "WHERE u.username = $username AND c.identifier = $identifier AND c.type = $type " +
            "MERGE (u)-[r:REACTED {reaction: $reaction}]->(c)")
    void react(String username, String identifier, TCG type, Reaction reaction);

    @Query("MATCH (u:User), (c:Card) " +
            "WHERE u.username = $username AND c.identifier = $identifier AND c.type = $type " +
            "AND EXISTS((u)-[r:REACTED {reaction: $reaction}]-(c)) " +
            "DELETE r")
    void reactDelete(String username, String identifier, TCG type, Reaction reaction);

}
