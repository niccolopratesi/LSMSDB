package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.service.MagicCardService;
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagicCardServiceImpl implements MagicCardService {

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;

    @Override
    public List<MagicCard> getMagicCardPage(int page) {
        if(page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE, Sort.by("name").ascending());
        Page<MagicCard> result = magicCardMongoRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public List<MagicCard> getMagicCardByParameters(
            String name,
            String type,
            String firstPrinting,
            String manaCost,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE, Sort.by("name").ascending());

        try {
            Page<MagicCard> result = magicCardMongoRepository.findByParameters(name, type, firstPrinting, manaCost, pageable);
            return result.getContent();
        } catch (Exception e){
            return null;
        }
    }
}
