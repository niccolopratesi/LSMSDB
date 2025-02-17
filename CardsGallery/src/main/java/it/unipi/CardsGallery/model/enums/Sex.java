package it.unipi.CardsGallery.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sex {
    M, F, NOT_SPECIFIED;

    @JsonCreator
    public static Sex fromString(String value) {
        if (value == null) return NOT_SPECIFIED;
        for (Sex sex : Sex.values()) {
            if (sex.name().equalsIgnoreCase(value)) {
                return sex;
            }
        }
        return NOT_SPECIFIED;
    }
}
