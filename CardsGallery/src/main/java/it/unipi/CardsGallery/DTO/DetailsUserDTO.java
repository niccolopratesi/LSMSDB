package it.unipi.CardsGallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsUserDTO {
    int followersCount;
    int followingCount;
    int friendsCount;
    int postsCount;
}
