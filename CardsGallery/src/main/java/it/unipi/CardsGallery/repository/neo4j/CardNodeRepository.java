package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.utilities.OldUserReact;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardNodeRepository  extends Neo4jRepository<CardNode,Long> {

    @Query("MATCH (c:Card {identifier: $identifier, type: $type}) " +
            "DETACH DELETE c")
    void delete(String identifier, TCG type);

    @Query("MATCH (c:Card {identifier: $identifier, type: $type}) " +
           "SET c.name = $name")
    void update(String identifier, TCG type, String name);

    @Query("MATCH (u:User {username: $username}), (c:Card {identifier: $identifier, type: $type}) " +
            "OPTIONAL MATCH (u)-[existing:REACTED]->(c) " +
            "WITH u, c, existing, existing.reaction AS oldReaction " +
            "MERGE (u)-[r:REACTED]->(c) " +
            "SET r.reaction = $reaction " +
            "RETURN oldReaction AS oldReaction, (existing IS NOT NULL) AS result")
    OldUserReact react(String username, String identifier, TCG type, Reaction reaction);

    @Query("MATCH (u:User {username: $username})-[r:REACTED {reaction: $reaction}]->(c:Card {identifier: $identifier, type: $type}) " +
            "DELETE r " +
            "RETURN COUNT(r) > 0")
    boolean reactDelete(String username, String identifier, TCG type, Reaction reaction);

    @Query("MATCH (u:User {username: $username})-[r:REACTED]->(c:Card {identifier: $identifier, type: $tcg}) " +
            "RETURN r.reaction")
    Optional<Reaction> getReact(String username, String identifier, TCG tcg);

    @Query("MATCH (user:User {username: $username})-[:FOLLOW]->(friend:User)-[:FOLLOW]->(user) " +
            "MATCH (friend)-[:REACTED]->(card:Card) " +
            "RETURN card, COUNT(friend) AS reactions_count " +
            "ORDER BY reactions_count DESC " +
            "LIMIT 10")
    List<CardNode> getCardFriendReactStatistics(String username);
}
