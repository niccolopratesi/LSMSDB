package it.unipi.CardsGallery.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.unipi.CardsGallery.utilities.Constants.NOT_NULL_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletePostDTO {
    @NotNull(message = NOT_NULL_MSG)
    private AuthDTO auth;

    @NotNull(message = NOT_NULL_MSG)
    private String postTitle;
}
