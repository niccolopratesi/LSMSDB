package it.unipi.CardsGallery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Pokemon")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonCard {
    @Id @GeneratedValue
    private int id;

    private String name;

    private int countReaction;
    private int level;
    private int hp;
    private int nationalPokedexNumbers;
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
}
