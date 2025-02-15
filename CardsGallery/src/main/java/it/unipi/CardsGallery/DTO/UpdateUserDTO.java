package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String newUsername;
    private String newPassword;

    private AuthDTO auth;
    private String newFirstName;
    private String newLastName;
    private Sex newSex;
    private String newBirthDate;
    private String newRegistrationDate;
    private String newProfession;
}
