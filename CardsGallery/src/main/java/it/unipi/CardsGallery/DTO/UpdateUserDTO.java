package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    String newPassword;
    User user;
}
