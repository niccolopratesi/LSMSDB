package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagicCardDTO {
    private AuthDTO auth;
    private MagicCard magic;
}
