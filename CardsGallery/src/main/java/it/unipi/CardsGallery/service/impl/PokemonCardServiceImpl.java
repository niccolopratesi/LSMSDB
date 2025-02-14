package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.model.mongo.PokemonCard;
import it.unipi.CardsGallery.repository.mongo.PokemonCardMongoRepository;
import it.unipi.CardsGallery.service.PokemonCardService;
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonCardServiceImpl implements PokemonCardService {
    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Override
    public List<PokemonCard> getPokemonCardPage(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("name").ascending());
        Page<PokemonCard> result = pokemonCardMongoRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public List<PokemonCard> getPokemonCardByParameters(
            String name,
            String rarity,
            String set,
            String artist,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE, Sort.by("name").ascending());

        try {
            Page<PokemonCard> result = pokemonCardMongoRepository.findByParameters(name, rarity, set, artist, pageable);
            return result.getContent();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
