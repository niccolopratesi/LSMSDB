package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCardDTO {
    private AuthDTO auth;
    private int cardListId;
    private int cardId;
}
