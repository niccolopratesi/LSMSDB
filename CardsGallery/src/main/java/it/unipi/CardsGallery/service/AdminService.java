package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AdminService {
    public void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void updateCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void insertCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void deletePost(AdminDeletePostDTO dpDTO) throws AuthenticationException, NoAdminException;
    public void deleteUser(UserDTO userDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
}
