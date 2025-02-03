package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReactionDTO {
    private AuthDTO auth;
    private int postId;
    private String typeReaction; //enum???
}
