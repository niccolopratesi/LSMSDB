package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private AuthDTO auth;
    private int cardListId;
    private Card card;
}
