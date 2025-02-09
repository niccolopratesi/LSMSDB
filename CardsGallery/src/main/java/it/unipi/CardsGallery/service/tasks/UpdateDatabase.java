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
            switch(request.type) {
                case CREATE:
                    if(request.data instanceof UserNode) {
                        userNodeRepository.save((UserNode) request.data);
                    } else if(request.data instanceof PostNode) {
                        postNodeRepository.save((PostNode) request.data);
                    } else if(request.data instanceof CardNode) {
                        cardNodeRepository.save((CardNode) request.data);
                    }
                    break;
                case UPDATE:
                    break;
                case DELETE:
                    break;
                case REACTION:
                    break;
                default:
                    break;
            }
        }
    }
}
