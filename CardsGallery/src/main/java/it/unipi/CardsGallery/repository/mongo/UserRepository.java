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

    public boolean existsUserByUsernameAndPassword(String username, String password);

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
    //@Query("{'username': ?0}")
    //public Optional<User> findUserByUsername(String username);

    //@Query("{'username': ?0}")
    public boolean existsUserByUsername(String username);

    //@Query("{'_id': ?0, 'username': ?01, 'password':  ?2}")
    public boolean existsByIdAndUsernameAndPassword(String id, String username, String password);

    boolean existsByUsernameAndPostsTitle(String username, String postTitle);

    @Query("{ 'username': ?0 }")
    @Update("{ '$pull': { 'posts': { 'title': ?1 } } }")
    void deletePostFromUser(String username, String postTitle);
}
