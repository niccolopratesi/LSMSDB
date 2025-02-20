package it.unipi.CardsGallery.utilities;

import it.unipi.CardsGallery.model.enums.Reaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OldUserReact {
    Reaction oldReaction;
    boolean result;
}
