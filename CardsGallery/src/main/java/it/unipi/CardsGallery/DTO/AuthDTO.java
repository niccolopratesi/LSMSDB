package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {
    private String username;
    private String password;
    private String id;

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
