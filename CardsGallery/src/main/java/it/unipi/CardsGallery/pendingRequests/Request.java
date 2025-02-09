package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.RequestType;

public class Request {
    public RequestType type;
    public Object data;
    public String username;

    public Request(RequestType type, Object data, String username) {
        this.type = type;
        this.data = data;
        this.username = username;
    }

    public Request(RequestType type, Object data) {
        this.type = type;
        this.data = data;
        this.username = null;
    }
}
