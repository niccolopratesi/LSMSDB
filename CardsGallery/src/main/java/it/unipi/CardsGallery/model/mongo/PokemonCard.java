package it.unipi.CardsGallery.model.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Pokemon")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCard {
    @Id @GeneratedValue
    private String id;

    private String name;
    private int level;
    private int hp;
    private int[] nationalPokedexNumbers;
    private int convertedRetreatCost;
    private String[] retreatCost;
    private String[] weaknesses;
    private String[] resistances;
    private String[] attacks;
    private String[] abilities;
    private String[] types;
    private String[] subTypes;
    private String evolvesFrom;
    private String set;
    private String artist;
    private String rarity;
    private String flavourText;

    private int likeCount;
    private int dislikeCount;
    private int loveCount;
    private int laughCount;

    public void defaultCounts() {
        likeCount = 0;
        dislikeCount = 0;
        loveCount = 0;
        laughCount = 0;
    }
}
