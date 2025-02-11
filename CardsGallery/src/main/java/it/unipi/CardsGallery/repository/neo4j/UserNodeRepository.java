package it.unipi.CardsGallery.repository.neo4j;

import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode,Long> {

    @Query("MATCH (u:User) " +
           "OPTIONAL MATCH (u)-[:CREATED]->(p:Post) " +
           "WHERE u.username = $username " +
           "DETACH DELETE u, p")
    void delete(String username);

    @Query("MATCH (u:User)" +
           "WHERE u.username = $oldUsername" +
           "SET u.username = $newUsername")
    void update(String oldUsername, String newUsername);

    @Query("MATCH (follower:User {username: $username}), (followee:User {username: $followingUsername}) " +
            "MERGE (follower)-[r:FOLLOWS]->(followee)")
    void follow(String username, String followingUsername);

    @Query("MATCH (follower:User {username: $username})-[r:FOLLOWS]->(followee:User {username: $unfollowingUsername}) " +
            "DELETE r")
    void unfollow(String username, String unfollowingUsername);

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
}
