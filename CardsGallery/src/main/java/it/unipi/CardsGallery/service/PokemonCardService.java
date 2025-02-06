package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.model.mongo.PokemonCard;

import java.util.List;
import java.util.Optional;

public interface PokemonCardService {
    List<PokemonCard> getPokemonCardPage(int page);

    List<PokemonCard> getPokemonCardByParameters(
            Optional<String> name,
            Optional<String> rarity,
            Optional<String> set,
            Optional<String> artist,
            int page
    );
}
