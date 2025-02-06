package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.model.mongo.PokemonCard;

import java.util.List;
import java.util.Optional;

public interface PokemonCardService {
    List<PokemonCard> getPokemonCardPage(int page);

    List<PokemonCard> getPokemonCardCardByParameters(
            Optional<String> name,
            Optional<String> type,
            Optional<String> firstPrinting,
            Optional<String> manaCost,
            int page
    );
}
