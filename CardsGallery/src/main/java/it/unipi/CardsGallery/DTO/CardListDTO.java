package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.CardList;
import lombok.Data;

@Data
public class CardListDTO {

    private int userId;
    private CardList cardList;
    private boolean status; //non servirebbe ma si può mettere a null l'oggetto CardList
}
