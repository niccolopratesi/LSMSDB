package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ReactionRequestData {
    private int[] reactionCount;

    public ReactionRequestData(Reaction reaction) {
        reactionCount = new int[4];
        Arrays.fill(reactionCount, 0);
    }

    public void incrementReaction(Reaction reaction) {
        reactionCount[reaction.ordinal()]++;
    }

    public void decrementReaction(Reaction reaction) {
        reactionCount[reaction.ordinal()]--;
    }
}
