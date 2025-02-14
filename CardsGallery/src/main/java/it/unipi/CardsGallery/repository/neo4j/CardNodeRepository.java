package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNodeRepository  extends Neo4jRepository<CardNode,Long> {

    @Query("MATCH (c:Card {identifier: $identifier, type: $type})" +
            "DETACH DELETE c")
    void delete(String identifier, TCG type);

    @Query("MATCH (c:Card {identifier: $identifier, type: $type})" +
           "SET c.name = $name")
    void update(String identifier, TCG type, String name);

    @Query("MATCH (u:User {username: $username}), (c:Card {identifier: $identifier, type: $type}) " +
            "MERGE (u)-[r:REACTED {reaction: $reaction}]->(c)" +
            "SET r.reaction = $reaction")
    void react(String username, String identifier, TCG type, Reaction reaction);

    @Query("MATCH (u:User {username: $username}), (c:Card {identifier: $identifier, type: $type}) " +
            "AND EXISTS((u)-[r:REACTED {reaction: $reaction}]-(c)) " +
            "DELETE r")
    void reactDelete(String username, String identifier, TCG type, Reaction reaction);

    @Query("MATCH (u:User {username: $username})-[r:REACTED]->(c:Card {identifier: $identifier, type: $tcg})" +
            "RETURN r.reaction")
    Reaction getReact(String username, String cardId, TCG tcg);
}
