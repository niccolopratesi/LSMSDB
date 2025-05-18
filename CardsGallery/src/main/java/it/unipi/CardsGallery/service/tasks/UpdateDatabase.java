package it.unipi.CardsGallery.service.tasks;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.*;
import it.unipi.CardsGallery.repository.mongo.MagicCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.PokemonCardMongoRepository;
import it.unipi.CardsGallery.repository.mongo.YugiohCardMongoRepository;
import it.unipi.CardsGallery.repository.neo4j.CardNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.PostNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.UserNodeRepository;
import it.unipi.CardsGallery.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class UpdateDatabase {

    @Autowired
    private UserNodeRepository userNodeRepository;

    @Autowired
    private PostNodeRepository postNodeRepository;

    @Autowired
    private CardNodeRepository cardNodeRepository;

    @Autowired
    private MagicCardMongoRepository magicCardMongoRepository;

    @Autowired
    private PokemonCardMongoRepository pokemonCardMongoRepository;

    @Autowired
    private YugiohCardMongoRepository yugiohCardMongoRepository;

    @Scheduled(fixedRate = 5000)
    public void updateDatabase() {
        while(!PendingRequests.pendingRequests.isEmpty()) {
            Request request = PendingRequests.pendingRequests.remove();
            switch(request.getType()) {
                case CREATE_USER:
                    userNodeRepository.save((UserNode) request.getData());
                    break;
                case CREATE_POST:
                    postNodeRepository.createPost(request.getUsername(), ((PostNode) request.getData()).getTitle());
                    break;
                case CREATE_CARD:
                    cardNodeRepository.save((CardNode) request.getData());
                    break;
                case UPDATE_USER:
                    userNodeRepository.update(((UserNode) request.getData()).getUsername(), request.getUsername());
                    break;
                case UPDATE_CARD:
                    cardNodeRepository.update(((CardNode) request.getData()).getIdentifier(), ((CardNode) request.getData()).getType(), ((CardNode) request.getData()).getName());
                    break;
                case DELETE_USER:
                    List<PendingReactions> pendingReactions = userNodeRepository.getAllUserCardReactions(((UserNode) request.getData()).getUsername());
                    for(PendingReactions pendingReaction : pendingReactions) {
                        ReactionRequest reactionRequest = new ReactionRequest(pendingReaction.getCardId(), pendingReaction.getTcg());
                        ReactionRequestData reactionRequestData = new ReactionRequestData();
                        PendingRequests.addOrUpdateReaction(reactionRequest, reactionRequestData, pendingReaction.getReaction(), Constants.DECREMENT);
                    }
                    userNodeRepository.delete(((UserNode) request.getData()).getUsername());
                    break;
                case DELETE_POST:
                    postNodeRepository.delete(request.getUsername(), ((PostNode) request.getData()).getTitle());
                    break;
                case DELETE_CARD:
                    cardNodeRepository.delete(((CardNode) request.getData()).getIdentifier(), ((CardNode) request.getData()).getType());
                    break;
                default:
                    break;
            }
        }
    }

    @Scheduled(fixedRate = 15000)
    public void updateReactions() {
        Iterator<Map.Entry<ReactionRequest, ReactionRequestData>> iterator = PendingRequests.pendingReactions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ReactionRequest, ReactionRequestData> entry = iterator.next();
            ReactionRequest reactionRequest = entry.getKey();
            ReactionRequestData reactionRequestData = entry.getValue();
            int likeCount = reactionRequestData.getReactionCount()[Reaction.LIKE.ordinal()];
            int dislikeCount = reactionRequestData.getReactionCount()[Reaction.DISLIKE.ordinal()];
            int loveCount = reactionRequestData.getReactionCount()[Reaction.LOVE.ordinal()];
            int laughCount = reactionRequestData.getReactionCount()[Reaction.LAUGH.ordinal()];
            switch (reactionRequest.getTcg()) {
                case MAGIC:
                    magicCardMongoRepository.updateReactions(reactionRequest.getCardId(), likeCount, dislikeCount, loveCount, laughCount);
                    break;
                case YUGIOH:
                    yugiohCardMongoRepository.updateReactions(reactionRequest.getCardId(), likeCount, dislikeCount, loveCount, laughCount);
                    break;
                case POKEMON:
                    pokemonCardMongoRepository.updateReactions(reactionRequest.getCardId(), likeCount, dislikeCount, loveCount, laughCount);
                    break;
                default:
                    break;
            }
            iterator.remove();
        }
    }
}
