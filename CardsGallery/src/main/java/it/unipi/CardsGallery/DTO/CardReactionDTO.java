package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardReactionDTO {
    private AuthDTO auth;
    private String cardId;
    private TCG type;
    private String typeReaction; //enum???
}
