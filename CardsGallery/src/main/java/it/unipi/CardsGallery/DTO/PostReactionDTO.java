package it.unipi.CardsGallery.DTO;

import it.unipi.CardsGallery.model.enums.Reaction;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.unipi.CardsGallery.utilities.Constants.NOT_NULL_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReactionDTO {
    @NotNull(message = NOT_NULL_MSG)
    private LoginDTO login;

    @NotNull(message = NOT_NULL_MSG)
    private String title;

    @NotNull(message = NOT_NULL_MSG)
    private String owner;

    private Reaction reaction;
}
