package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Sex;
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
    private Sex sex;
    private String birthDate;
    private String registrationDate;
    private String profession;
}
