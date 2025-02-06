package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardListDTO {
    private AuthDTO auth;
    private String cardListName;
    private boolean status; //va messo in fase di creazione??
}
