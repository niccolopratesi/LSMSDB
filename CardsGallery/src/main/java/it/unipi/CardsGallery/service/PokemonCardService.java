package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.PokemonCard;

import java.util.List;

public interface PokemonCardService {
    List<PokemonCard> getPokemonCardPage(int page);

    List<PokemonCard> getPokemonCardByParameters(
            String name,
            String rarity,
            String set,
            String artist,
            int page
    );
}
