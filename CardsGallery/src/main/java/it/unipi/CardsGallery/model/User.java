package it.unipi.CardsGallery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private char sex;
    private Date birthDate;
    private Date registrationDate;
    private String profession;
    private List<Post> posts;

    //Admin bool?????
    private boolean admin;
}
