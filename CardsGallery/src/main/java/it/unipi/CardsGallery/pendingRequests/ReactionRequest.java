package it.unipi.CardsGallery.pendingRequests;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ReactionRequest {
    private String cardId;
    private TCG tcg;

    public ReactionRequest(String cardId, TCG tcg) {
        this.cardId = cardId;
        this.tcg = tcg;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReactionRequest that = (ReactionRequest) obj;
        return this.cardId.equals(that.cardId) && (tcg == that.tcg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, tcg);
    }
}
