package it.unipi.CardsGallery.DTO;

import lombok.Data;

@Data
public class ReactionDTO {
    private int userId;
    private int cardId;
    private int postId;
    private String TCG; //enum???
    private String typeReaction; //enum???
}
