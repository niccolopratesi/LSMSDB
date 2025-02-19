package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReactionCount {
    private Reaction reaction;
    private int count;
}

