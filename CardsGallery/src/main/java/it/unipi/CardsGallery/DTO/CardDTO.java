package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.Card;
import lombok.Data;

@Data
public class CardDTO {
    private int userId;
    private int cardListId;
    private Card card;
}
