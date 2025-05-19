package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.RequestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    private RequestType type;
    private Object data;
    private String username;
    private Reaction reaction;
    private String postOwner;

    public Request(RequestType type, Object data, String username) {
        this.type = type;
        this.data = data;
        this.username = username;
        this.reaction = null;
        this.postOwner = null;
    }

    public Request(RequestType type, Object data) {
        this.type = type;
        this.data = data;
        this.username = null;
        this.reaction = null;
        this.postOwner = null;
    }
}
