package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.model.mongo.PokemonCard;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCardDTO {
    private AuthDTO auth;
    private TCG type;
    private MagicCard magic;
    private YugiohCard yugioh;
    private PokemonCard pokemon;
}
