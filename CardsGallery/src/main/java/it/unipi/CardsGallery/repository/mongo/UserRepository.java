package it.unipi.CardsGallery.repository.mongo;

import it.unipi.CardsGallery.DTO.AuthDTO;
import it.unipi.CardsGallery.model.mongo.Post;
import it.unipi.CardsGallery.model.mongo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //public Optional<User> findUserByUsernameAndPassword(String username, String password);

    boolean existsUserByUsernameAndPassword(String username, String password);

    @Aggregation(pipeline = {
            "{ '$match': { 'username': ?0 } }",
            "{ '$project': { 'posts': 1, '_id': 0 } }",
            "{ '$unwind': '$posts' }",
            "{ '$replaceRoot': { 'newRoot': '$posts' } }",
            "{ '$skip':  ?1 }",
            "{ '$limit' :  ?2}"
    })
    List<Post> findPostsByUsername(String username, int skip, int limit);

    @Query("{ 'id': ?0 }")
    @Update("{ '$push': { 'posts': ?1 } }")
    void addPostToUser(String id, Post post);

    @Query(value = "{ 'username': ?0}" , fields = "{'password': 0, 'admin': 0}")
    List<User> findUserByUsername(String username);

    boolean existsUserByUsername(String username);

    @Query(value = "{ 'id': ?0,'username': ?1, 'password': ?2}" , fields = "{'admin': 1, '_id': 0}")
    User findByIdAndUsernameAndPassword(String id, String username, String password);

    @Query(value = "{ 'username': ?0, 'password': ?1}" , fields = "{'admin': 1, '_id': 0}")
    User findByUsernameAndPassword(String username, String password);

    boolean existsByUsernameAndPostsTitle(String username, String postTitle);

    @Query("{ 'username': ?0 }")
    @Update("{ '$pull': { 'posts': { 'title': ?1 } } }")
    void deletePostFromUser(String username, String postTitle);

    void deleteByUsername(String username);

    @Query(value = "{ 'username': ?0 }" , fields = "{'password': 1, 'admin': 1, 'id': 1}")
    User getUserByUsername(String username);

    @Query("{ '_id': ?0, 'username': ?1 }")
    User getUserByIdAndUsername(String id, String username);
}
