package it.unipi.CardsGallery.service.impl;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.repository.mongo.*;
import it.unipi.CardsGallery.service.AdminService;
import it.unipi.CardsGallery.service.AuthenticationService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import it.unipi.CardsGallery.model.enums.TCG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardListRepository cardListRepository;

    @Autowired
    PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    YugiohCardMongoRepository yugiohCardMongoRepository;

    @Autowired
    MagicCardMongoRepository magicCardMongoRepository;


    @Override
    public void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardNoFieldsDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardNoFieldsDTO.getType();
        String id = adminCardNoFieldsDTO.getId();
        switch (type) {
            case MAGIC -> magicCardMongoRepository.deleteById(id);
            case POKEMON -> pokemonCardMongoRepository.deleteById(id);
            case YUGIOH -> yugiohCardMongoRepository.deleteById(id);
            default -> throw new NoAdminException("Type not supported");
        }
        cardListRepository.removeCardFromAllCardList(id);
    }

    @Override
    public void updateCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardDTO.getType();
        String id;
        switch (type) {
            case MAGIC:
            id = adminCardDTO.getMagic().getId();
            if(magicCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            magicCardMongoRepository.save(adminCardDTO.getMagic());
            break;

            case POKEMON:
            id = adminCardDTO.getPokemon().getId();
            if(pokemonCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            pokemonCardMongoRepository.save(adminCardDTO.getPokemon());
            break;

            case YUGIOH:
            id = adminCardDTO.getYugioh().getId();
            if(yugiohCardMongoRepository.existsById(id))
                throw new ExistingEntityException("Card not found");
            yugiohCardMongoRepository.save(adminCardDTO.getYugioh());
            break;

            default:
                throw new ExistingEntityException("Invalid TCG type");
        }
    }

    @Override
    public void insertCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = adminCardDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        TCG type = adminCardDTO.getType();
        switch (type) {
            case MAGIC:
            adminCardDTO.getMagic().setId(null);
            magicCardMongoRepository.save(adminCardDTO.getMagic());
            break;

            case POKEMON:
            adminCardDTO.getPokemon().setId(null);
            pokemonCardMongoRepository.save(adminCardDTO.getPokemon());
            break;

            case YUGIOH:
            adminCardDTO.getYugioh().setId(null);
            yugiohCardMongoRepository.save(adminCardDTO.getYugioh());
            break;

            default:
            throw new ExistingEntityException("Invalid TCG type");
        }
    }

    @Override
    public void deletePost(DeletePostDTO dpDTO) throws AuthenticationException, NoAdminException {
        AuthDTO authDTO = dpDTO.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        userRepository.deletePostFromUser(dpDTO.getAuth().getUsername(), dpDTO.getPostTitle());
    }

    @Override
    public void deleteUser(UserDTO dto) throws AuthenticationException, NoAdminException, ExistingEntityException {
        AuthDTO authDTO = dto.getAuth();
        authenticationService.authenticateAdmin(authDTO);
        if(!userRepository.existsUserByUsername(dto.getUsername()))
            throw new ExistingEntityException("User not found");
        userRepository.deleteByUsername(dto.getUsername());
        cardListRepository.deleteAllByUsername(dto.getUsername());
    }
}
