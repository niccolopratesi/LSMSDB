package it.unipi.CardsGallery.service;

import it.unipi.CardsGallery.DTO.AdminCardNoFieldsDTO;
import it.unipi.CardsGallery.DTO.AdminCardDTO;
import it.unipi.CardsGallery.DTO.DeletePostDTO;
import it.unipi.CardsGallery.DTO.UserDTO;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;

public interface AdminService {
    public void deleteCard(AdminCardNoFieldsDTO adminCardNoFieldsDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void updateCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void insertCard(AdminCardDTO adminCardDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
    public void deletePost(DeletePostDTO dpDTO) throws AuthenticationException, NoAdminException;
    public void deleteUser(UserDTO userDTO) throws AuthenticationException, NoAdminException, ExistingEntityException;
}
