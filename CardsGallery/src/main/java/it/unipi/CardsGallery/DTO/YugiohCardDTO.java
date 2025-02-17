package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.YugiohCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YugiohCardDTO {
    private AuthDTO auth;
    private YugiohCard yugioh;
}
