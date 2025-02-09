package it.unipi.CardsGallery.service.tasks;

import it.unipi.CardsGallery.model.enums.RequestType;
import it.unipi.CardsGallery.model.neo4j.CardNode;
import it.unipi.CardsGallery.model.neo4j.PostNode;
import it.unipi.CardsGallery.model.neo4j.UserNode;
import it.unipi.CardsGallery.pendingRequests.PendingRequests;
import it.unipi.CardsGallery.pendingRequests.Request;
import it.unipi.CardsGallery.repository.neo4j.CardNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.PostNodeRepository;
import it.unipi.CardsGallery.repository.neo4j.UserNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateDatabase {

    @Autowired
    private UserNodeRepository userNodeRepository;

    @Autowired
    private PostNodeRepository postNodeRepository;

    @Autowired
    private CardNodeRepository cardNodeRepository;

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
                case CREATE_POST_REACTION:
                    break;
                case DELETE_POST_REACTION:
                    break;
                case CREATE_CARD_REACTION:
                    break;
                case DELETE_CARD_REACTION:
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
}
