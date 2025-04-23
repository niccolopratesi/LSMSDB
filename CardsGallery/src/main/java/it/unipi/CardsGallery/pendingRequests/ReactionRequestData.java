package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionRequestData {
    // DELETE
    private int likeCount;
    private int dislikeCount;
    private int loveCount;
    private int laughCount;
    // DELETE

    private int[] reactionCount;

    public ReactionRequestData(Reaction reaction) {
        // DELETE
        likeCount = 0;
        dislikeCount = 0;
        loveCount = 0;
        laughCount = 0;
        // DELETE

        for(int i = 0; i < reactionCount.length; i++) {
            reactionCount[i] = 0;
        }

        // DELETE
        //incrementReaction(reaction);
    }

    // DELETE
    private void incLike() {
        likeCount++;
    }
    private void incDislike() {
        dislikeCount++;
    }
    private void incLove() {
        loveCount++;
    }
    private void incLaugh() {
        laughCount++;
    }
    private void decLike() {
        likeCount--;
    }
    private void decDislike() {
        dislikeCount--;
    }
    private void decLove() {
        loveCount--;
    }
    private void decLaugh() {
        laughCount--;
    }
    // DELETE

    public void incrementReaction(Reaction reaction) {

        reactionCount[reaction.ordinal()]++;

        // DELETE
        switch (reaction) {
            case LIKE:
                this.incLike();
                break;
            case DISLIKE:
                this.incDislike();
                break;
            case LOVE:
                this.incLove();
                break;
            case LAUGH:
                this.incLaugh();
                break;
            default:
                break;
        }
    }

    public void decrementReaction(Reaction reaction) {

        reactionCount[reaction.ordinal()]--;

        // DELETE
        switch (reaction) {
            case LIKE:
                this.decLike();
                break;
            case DISLIKE:
                this.decDislike();
                break;
            case LOVE:
                this.decLove();
                break;
            case LAUGH:
                this.decLaugh();
                break;
            default:
                break;
        }
        // DELETE
    }
}
