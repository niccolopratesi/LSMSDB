package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.model.mongo.MagicCard;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.service.MagicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MagicCardServiceImpl implements MagicCardService {

    @Autowired
    MagicCardMongoRepository magicCardMongoRepository;

    @Override
    public List<MagicCard> getMagicCardPage(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("id").ascending()); // Sorting optional
        Page<MagicCard> result = magicCardMongoRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public List<MagicCard> getMagicCardByParameters(
            Optional<String> name,
            Optional<String> type,
            Optional<String> firstPrinting,
            Optional<String> manaCost,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("id").ascending()); // Sorting optional

        String n = name.orElse(".*");
        String t = type.orElse(".*");
        String f = firstPrinting.orElse(".*");
        String m = manaCost.orElse(".*");

        try {
            Page<MagicCard> result = magicCardMongoRepository.findByParameters(n, t, f, m, pageable);
            return result.getContent();
        } catch (Exception e){
            return null;
        }
    }
}
