package it.unipi.CardsGallery.DTO.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagicCostColorDTO {
    private String color;
    private Float avgManaValue;
}
