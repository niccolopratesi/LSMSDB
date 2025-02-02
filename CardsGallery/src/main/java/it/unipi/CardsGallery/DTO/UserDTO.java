package it.unipi.CardsGallery.DTO;

import lombok.Data;

@Data
public class UserDTO
{
    private int userId;
    private String otherUsername; //utente da seguire o smettere di seguire
}
