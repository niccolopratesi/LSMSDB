package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardReactionDTO {
    private AuthDTO auth;
    private String cardId;
    private String TCG; //enum???
    private String typeReaction; //enum???
}
