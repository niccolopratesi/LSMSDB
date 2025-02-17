package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.PokemonCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCardDTO {
    private AuthDTO auth;
    private PokemonCard pokemon;
}
