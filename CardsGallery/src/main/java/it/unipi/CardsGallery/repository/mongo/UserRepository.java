package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.DTO.statistics.UserMostPostsDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'username': ?0 } }",
            "{ '$project': { 'posts': 1, '_id': 0 } }",
            "{ '$unwind': '$posts' }",
            "{ '$replaceRoot': { 'newRoot': '$posts' } }",
            "{ '$skip':  ?1 }",
            "{ '$limit' :  ?2}"
    })
    List<Post> findPostsByUsername(String username, int skip, int limit);

    @Query("{ 'username': ?0 }")
    @Update("{ '$push': { 'posts': {$each: [?1], '$position':  0} } }")
    void addPostToUser(String username, Post post);

    @Query(value = "{ 'username': ?0}" , fields = "{'password': 0, 'admin': 0}")
    User findUserByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsByUsernameAndPostsTitle(String username, String postTitle);


    @Query("{ 'username': ?0 }")
    @Update("{ '$pull': { 'posts': { 'title': ?1 } } }")
    int deletePostFromUser(String username, String postTitle);

    int deleteByUsername(String username);

    @Query(value = "{ 'username': ?0 }" , fields = "{'username': 1, 'password': 1, 'admin': 1, 'id': 1}")
    User getUserByUsername(String username);

    @Aggregation(pipeline = {
            "{ $project: { username: 1, posts: { $size: '$posts' } } }",
            "{ $sort: { posts: -1 } }",
            "{ $limit: 1 }",
            "{ $project: { _id: 0, username: 1, posts: 1 } }"
    })
    UserMostPostsDTO getUserMostPostsStatistics();
}
