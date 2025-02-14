package it.unipi.CardsGallery.DTO.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagicColorRatioDTO {
    Integer monocolor;
    Integer multicolor;
    Float ratio;
}
