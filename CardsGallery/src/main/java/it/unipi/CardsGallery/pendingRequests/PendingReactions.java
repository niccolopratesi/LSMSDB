package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingReactions {
    String id;
    TCG tcg;
    Reaction reaction;
}
