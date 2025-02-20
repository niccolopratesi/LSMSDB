package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PendingRequests {
    public static ConcurrentLinkedQueue<Request> pendingRequests = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<ReactionRequest, ReactionRequestData> pendingReactions = new ConcurrentHashMap<>();

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
            if(action) {
                reactionRequestData.incrementReaction(reaction);
            } else {
                reactionRequestData.decrementReaction(reaction);
            }
            pendingReactions.put(reactionRequest, reactionRequestData);
        }
    }
}
