package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCardNoFieldsDTO {
    private AuthDTO auth;
    private TCG type;
    private String id;
}
