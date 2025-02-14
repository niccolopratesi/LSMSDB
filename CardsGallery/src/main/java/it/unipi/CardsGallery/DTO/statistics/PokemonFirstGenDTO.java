package it.unipi.CardsGallery.DTO.statistics;

import it.unipi.CardsGallery.model.mongo.PokemonCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonFirstGenDTO {
    Integer count;
    String set;
}
