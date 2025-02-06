package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.CommonConstants;
import it.unipi.CardsGallery.model.mongo.YugiohCard;
import it.unipi.CardsGallery.repository.mongo.YugiohCardMongoRepository;
import it.unipi.CardsGallery.service.YugiohCardService;
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
    YugiohCardMongoRepository yugiohCardMongoRepository;

    @Override
    public List<YugiohCard> getYugiohCardPage(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("id").ascending()); // Sorting optional
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
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE, Sort.by("id").ascending()); // Sorting optional

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
