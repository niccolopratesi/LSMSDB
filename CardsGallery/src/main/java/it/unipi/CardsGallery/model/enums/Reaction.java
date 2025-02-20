package it.unipi.CardsGallery.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Reaction {
    LIKE,
    DISLIKE,
    LOVE,
    LAUGH;

    @JsonCreator
    public static Reaction fromString(String value) {
        if(value == null) return null;
        for (Reaction reaction : Reaction.values()) {
            if (reaction.name().equalsIgnoreCase(value)) {
                return reaction;
            }
        }
        return null;
    }
}
