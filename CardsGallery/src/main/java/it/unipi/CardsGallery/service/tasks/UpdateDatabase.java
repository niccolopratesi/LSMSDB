package it.unipi.CardsGallery.service.tasks;

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
                case CREATE:
                    if(request.getData() instanceof UserNode) {
                        userNodeRepository.save((UserNode) request.getData());
                    } else if(request.getData() instanceof PostNode) {
                        postNodeRepository.createPost(request.getUsername(), ((PostNode) request.getData()).getTitle());
                    } else if(request.getData() instanceof CardNode) {
                        cardNodeRepository.save((CardNode) request.getData());
                    }
                    break;
                case UPDATE:
                    if(request.getData() instanceof UserNode) {
                        userNodeRepository.update(((UserNode) request.getData()).getUsername(), request.getUsername());
                    } else if(request.getData() instanceof CardNode) {
                        cardNodeRepository.update(((CardNode) request.getData()).getIdentifier(), ((CardNode) request.getData()).getType(), ((CardNode) request.getData()).getName());
                    }
                    break;
                case DELETE:
                    if(request.getData() instanceof UserNode) {
                        List<PendingReactions> pendingReactions = userNodeRepository.getAllUserCardReactions(((UserNode) request.getData()).getUsername());
                        for(PendingReactions pendingReaction : pendingReactions) {
                            ReactionRequest reactionRequest = new ReactionRequest(pendingReaction.getCardId(), pendingReaction.getTcg());
                            ReactionRequestData reactionRequestData = new ReactionRequestData(pendingReaction.getReaction());
                            PendingRequests.addOrUpdateReaction(reactionRequest, reactionRequestData, pendingReaction.getReaction(), Constants.DECREMENT);
                        }

                        userNodeRepository.delete(((UserNode) request.getData()).getUsername());
                    } else if(request.getData() instanceof PostNode) {
                        postNodeRepository.delete(request.getUsername(), ((PostNode) request.getData()).getTitle());
                    } else if(request.getData() instanceof CardNode) {
                        cardNodeRepository.delete(((CardNode) request.getData()).getIdentifier(), ((CardNode) request.getData()).getType());
                    }
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

            int likeCount = reactionRequestData.getLikeCount();
            int dislikeCount = reactionRequestData.getDislikeCount();
            int loveCount = reactionRequestData.getLoveCount();
            int laughCount = reactionRequestData.getLaughCount();

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
