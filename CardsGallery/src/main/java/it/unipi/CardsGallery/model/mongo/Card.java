package it.unipi.CardsGallery.model.mongo;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    private String id;

    private String name;
    private TCG tcg;
    private String printingSet;

    //dati utili per le query
    private int[] pokedexNumber;

    private List<String> colors;

    private String type;
    private String attribute;
}
