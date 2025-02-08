package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String newUsername;
    private String newPassword;

    private AuthDTO auth;
    private String firstName;
    private String lastName;
    private char sex;
    private Date birthDate;
    private Date registrationDate;
    private String profession;
}
