package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AdminService {
    void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void deletePost(AdminDeletePostDTO dpDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void deleteUser(AdminDeleteUserDTO userDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void insertMagicCard(MagicCardDTO newMagicCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void insertPokemonCard(PokemonCardDTO newPokemonCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void insertYugiohCard(YugiohCardDTO newYugiohCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void updateMagicCard(MagicCardDTO magicCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void updatePokemonCard(PokemonCardDTO pokemonCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void updateYugiohCard(YugiohCardDTO yugiohCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
}
