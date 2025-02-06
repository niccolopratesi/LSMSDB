package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.model.mongo.YugiohCard;

import java.util.List;
import java.util.Optional;

public interface YugiohCardService {
    List<YugiohCard> getYugiohCardPage(int page);

    List<YugiohCard> getYugiohCardByParameters(
            Optional<String> name,
            Optional<String> type,
            Optional<String> firstPrinting,
            Optional<String> manaCost,
            int page
    );
}
