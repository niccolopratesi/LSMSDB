package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPostReactionDTO {
    private int likeCount;
    private int dislikeCount;
    private int loveCount;
    private int laughCount;
    private Reaction reaction;

    public ResultPostReactionDTO(List<ReactionCount> set, Reaction reaction) {
        this.reaction = reaction;
        for (ReactionCount reactionCount : set) {
            Reaction r = reactionCount.getReaction();
            int count = reactionCount.getCount();
            switch (r) {
                case LIKE:
                likeCount = count;
                break;

                case DISLIKE:
                dislikeCount = count;
                break;

                case LOVE:
                loveCount = count;
                break;

                case LAUGH:
                laughCount = count;
                break;
            }
        }
    }
}
