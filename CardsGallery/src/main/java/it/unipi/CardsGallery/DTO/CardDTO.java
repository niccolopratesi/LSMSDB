package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private AuthDTO auth;
    private String cardListId;
    private String cardId;
    private TCG tcg;
}
