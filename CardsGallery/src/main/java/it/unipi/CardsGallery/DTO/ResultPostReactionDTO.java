package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPostReactionDTO {
    private int likeCount;
    private int dislikeCount;
    private int loveCount;
    private int laughCount;
    private Reaction reaction;
}
