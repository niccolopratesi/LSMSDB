package it.unipi.CardsGallery.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pokemon")
public class PokemonCard {
    private int id;
    private String name;
    private String description;

}
