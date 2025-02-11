package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.MagicCard;

import java.util.List;
import java.util.Optional;

public interface MagicCardService {
    List<MagicCard> getMagicCardPage(int page);

    List<MagicCard> getMagicCardByParameters(
            String name,
            String type,
            String firstPrinting,
            String manaCost,
            int page
    );
}
