package it.unipi.CardsGallery.model.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.unipi.CardsGallery.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Sex sex;
    private String birthDate;
    private String registrationDate;
    private String profession;
    private List<Post> posts;
    private Boolean admin;

    public void createRegistrationDate(){
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        registrationDate = currentDate.format(formatter);
    }
}
