package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.Post;
import lombok.Data;

@Data
public class PostDTO {
    private int userId;
    private Post post;
    private int postId; //non servirebbe ma si può usare e mettere null Post per cancellare il post
}
