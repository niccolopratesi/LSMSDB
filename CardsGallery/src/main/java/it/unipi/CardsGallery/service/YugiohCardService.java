package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.model.mongo.YugiohCard;

import java.util.List;

public interface YugiohCardService {
    List<YugiohCard> getYugiohCardPage(int page);

    List<YugiohCard> getYugiohCardByParameters(
            String name,
            String attribute,
            String race,
            String printing,
            int page
    );
}
