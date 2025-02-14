package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AdminService {
    void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void updateCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void insertCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    void deletePost(AdminDeletePostDTO dpDTO) throws AuthenticationException, NoAdminException;
    void deleteUser(UserDTO userDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
}
