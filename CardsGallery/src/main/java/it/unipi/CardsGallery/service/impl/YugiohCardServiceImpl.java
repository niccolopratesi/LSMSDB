package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.repository.mongo.YugiohCardMongoRepository;
import it.unipi.CardsGallery.service.YugiohCardService;
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
public class YugiohCardServiceImpl implements YugiohCardService {

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;

    @Override
    public List<YugiohCard> getYugiohCardPage(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("id").ascending());
        Page<YugiohCard> result = yugiohCardMongoRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public List<YugiohCard> getYugiohCardByParameters(
            Optional<String> name,
            Optional<String> attribute,
            Optional<String> race,
            Optional<String> printing,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE, Sort.by("id").ascending());

        String n = name.orElse(".*");
        String a = attribute.orElse(".*");
        String r = race.orElse(".*");
        String p = printing.orElse(".*");

        try {
            Page<YugiohCard> result = yugiohCardMongoRepository.findByParameters(n, a, r, p, pageable);
            return result.getContent();
        } catch (Exception e){
            return null;
        }
    }
}
