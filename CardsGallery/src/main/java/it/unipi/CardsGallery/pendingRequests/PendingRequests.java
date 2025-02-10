package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PendingRequests {
    public static Queue<Request> pendingRequests = new LinkedList<>();
    public static HashMap<ReactionRequest, ReactionRequestData> pendingReactions = new HashMap<>();

    public static void addOrUpdateReaction(ReactionRequest reactionRequest, ReactionRequestData reactionRequestData, Reaction reaction, boolean action) {

        if (pendingReactions.containsKey(reactionRequest)) {
            if(action) {
                pendingReactions.get(reactionRequest).incrementReaction(reaction);
            } else {
                pendingReactions.get(reactionRequest).decrementReaction(reaction);
            }
        } else {
            if(reactionRequestData == null) {
                return;
            }
            pendingReactions.put(reactionRequest, reactionRequestData);
        }
    }
}
