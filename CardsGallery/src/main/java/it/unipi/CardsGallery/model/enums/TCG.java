package it.unipi.CardsGallery.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TCG {
    MAGIC, POKEMON, YUGIOH, UNDEFINED;

    @JsonCreator
    public static TCG fromString(String value) {
        if(value == null) return UNDEFINED;
        for (TCG tcg : TCG.values()) {
            if (tcg.name().equalsIgnoreCase(value)) {
                return tcg;
            }
        }
        return UNDEFINED;
    }
}
