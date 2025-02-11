package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.statistics.MagicCostColorDTO;
import it.unipi.CardsGallery.DTO.statistics.YugiohAttributeListDTO;
import it.unipi.CardsGallery.model.mongo.YugiohCard;

import java.util.List;

public interface StatisticsService {
    public List<YugiohCard> yugiohCardStatistics();
    public List<YugiohAttributeListDTO> yugiohListsStatistics();
    public List<MagicCostColorDTO> magicCardStatistics();
}
