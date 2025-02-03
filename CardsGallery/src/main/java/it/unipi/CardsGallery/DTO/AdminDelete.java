package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDelete {
    private AuthDTO auth;
    private int id;
}
