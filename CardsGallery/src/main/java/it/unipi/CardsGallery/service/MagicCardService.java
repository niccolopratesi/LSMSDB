package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.MagicCard;

import java.util.List;
import java.util.Optional;

public interface MagicCardService {
    List<MagicCard> getMagicCardPage(int page);

    List<MagicCard> getMagicCardByParameters(
            Optional<String> name,
            Optional<String> type,
            Optional<String> firstPrinting,
            Optional<String> manaCost,
            int page
    );
}
