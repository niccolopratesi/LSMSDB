package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Sex;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.unipi.CardsGallery.utilities.Constants.NOT_NULL_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    @NotNull(message = NOT_NULL_MSG)
    private AuthDTO auth;

    private String newUsername;
    private String newPassword;
    private String newFirstName;
    private String newLastName;
    private Sex newSex;
    private String newBirthDate;
    private String newRegistrationDate;
    private String newProfession;
}
