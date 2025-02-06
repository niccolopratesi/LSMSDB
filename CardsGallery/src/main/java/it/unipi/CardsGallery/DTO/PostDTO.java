package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.mongo.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private AuthDTO auth;
    private Post post;
}
