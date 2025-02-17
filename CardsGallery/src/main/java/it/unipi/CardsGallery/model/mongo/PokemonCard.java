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
    private String level;
    private String hp;
    private int[] nationalPokedexNumbers;
    private Integer convertedRetreatCost;
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

    private Integer likeCount;
    private Integer dislikeCount;
    private Integer loveCount;
    private Integer laughCount;

    public void defaultCounts() {
        likeCount = 0;
        dislikeCount = 0;
        loveCount = 0;
        laughCount = 0;
    }

    public void updateCard(PokemonCard card) {
        if(card.getName() != null) {
            this.name = card.getName();
        }
        if(card.getLevel() != null) {
            this.level = card.getLevel();
        }
        if(card.getHp() != null) {
            this.hp = card.getHp();
        }
        if(card.getNationalPokedexNumbers() != null) {
            this.nationalPokedexNumbers = card.getNationalPokedexNumbers();
        }
        if(card.getConvertedRetreatCost() != null) {
            this.convertedRetreatCost = card.getConvertedRetreatCost();
        }
        if(card.getRetreatCost() != null) {
            this.retreatCost = card.getRetreatCost();
        }
        if(card.getWeaknesses() != null) {
            this.weaknesses = card.getWeaknesses();
        }
        if(card.getResistances() != null) {
            this.resistances = card.getResistances();
        }
        if(card.getAttacks() != null) {
            this.attacks = card.getAttacks();
        }
        if(card.getAbilities() != null) {
            this.abilities = card.getAbilities();
        }
        if(card.getTypes() != null) {
            this.types = card.getTypes();
        }
        if(card.getSubTypes() != null) {
            this.subTypes = card.getSubTypes();
        }
        if(card.getEvolvesFrom() != null) {
            this.evolvesFrom = card.getEvolvesFrom();
        }
        if(card.getSet() != null) {
            this.set = card.getSet();
        }
        if(card.getArtist() != null) {
            this.artist = card.getArtist();
        }
        if(card.getRarity() != null) {
            this.rarity = card.getRarity();
        }
        if(card.getFlavourText() != null) {
            this.flavourText = card.getFlavourText();
        }
    }
}
