package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Reaction;
import it.unipi.CardsGallery.model.enums.TCG;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.unipi.CardsGallery.utilities.Constants.NOT_NULL_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardReactionDTO {
    @NotNull(message = NOT_NULL_MSG)
    private LoginDTO login;

    @NotNull(message = NOT_NULL_MSG)
    private String cardId;

    @NotNull(message = NOT_NULL_MSG)
    private TCG type;

    //@NotNull(message = NOT_NULL_MSG)
    private Reaction reaction;
}
