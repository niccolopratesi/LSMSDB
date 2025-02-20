package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.DTO.statistics.MostActiveUsersDTO;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.PendingReactions;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode,Long> {

    @Query("MATCH (u:User {username: $username}) " +
           "OPTIONAL MATCH (u)-[:CREATED]->(p:Post) " +
           "DETACH DELETE u, p")
    void delete(String username);

    @Query("MATCH (u:User {username: $username})-[r:REACTED]->(c:Card) " +
            "RETURN r.reaction AS reaction, c.identifier AS id, c.tcg AS tcg")
    List<PendingReactions> getAllUserCardReactions(String username);

    @Query("MATCH (u:User) " +
           "WHERE u.username = $oldUsername " +
           "SET u.username = $newUsername")
    void update(String oldUsername, String newUsername);

    @Query("MATCH (follower:User {username: $username}), (followee:User {username: $followingUsername}) " +
            "WHERE followee IS NOT NULL " +
            "MERGE (follower)-[r:FOLLOWS]->(followee) " +
            "RETURN CASE WHEN r IS NOT NULL THEN true ELSE false END")
    Boolean follow(String username, String followingUsername);

    @Query("MATCH (follower:User {username: $username})-[r:FOLLOWS]->(followee:User {username: $unfollowingUsername}) " +
            "DELETE r " +
            "RETURN TRUE")
    Boolean unfollow(String username, String unfollowingUsername);

    @Query("MATCH (follower:User)-[:FOLLOWS]->(u:User {username: $username}) " +
            "RETURN count(follower)")
    int getFollowersCount(String username);

    @Query("MATCH (u:User {username: $username})-[:FOLLOWS]->(followed:User) " +
            "RETURN count(followed)")
    int getFollowingCount(String username);

    @Query("MATCH (u:User {username: $username})-[:FOLLOWS]->(friend:User) " +
            "WHERE (friend)-[:FOLLOWS]->(u) " +
            "RETURN count(friend) ")
    int getFriendsCount(String username);

    @Query("MATCH (u:User {username: $username})-[:CREATED]->(p:Post) " +
            "RETURN count(p)")
    int getPostsCount(String username);

    @Query("MATCH (me:User {username: $username})-[:FOLLOWS]->(followed:User) " +
            "OPTIONAL MATCH (followed)-[:CREATED]->(p:Post) " +
            "OPTIONAL MATCH (other:User)-[r:REACTED]->(p) " +
            "RETURN followed.username AS username, COUNT(DISTINCT p) AS posts, COUNT(DISTINCT r) AS reacts " +
            "ORDER BY posts + reactions DESC " +
            "LIMIT 5")
    List<MostActiveUsersDTO> getMostActiveUsersStatistics(String username);

    @Query("MATCH (u:User {username: $username})-[r1:REACTED]->(c:Card)<-[r2:REACTED]-(other:User) " +
            "WHERE u <> other AND r1.type = r2.type " +
            "WITH other, COUNT(DISTINCT c) AS commonCards " +
            "RETURN DISTINCT other.username " +
            "ORDER BY commonCards DESC "+
            "LIMIT 5")
    List<String> getUsersCommonReactStatistics(String username);

    @Query("MATCH (u:User {username: $username})-[:FOLLOWS]->(friend:User)-[:FOLLOWS]->(u) " +
            "MATCH (friend)-[:FOLLOWS]->(fof:User)-[:FOLLOWS]->(friend) " +
            "WHERE fof <> u AND NOT (u)-[:FOLLOWS]->(fof) " +
            "RETURN DISTINCT fof.username "+
            "LIMIT 10")
    List<String> getRecommendedUsers(String username);
}
