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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountReaction() {
        return countReaction;
    }

    public void setCountReaction(int countReaction) {
        this.countReaction = countReaction;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getNationalPokedexNumbers() {
        return nationalPokedexNumbers;
    }

    public void setNationalPokedexNumbers(int nationalPokedexNumbers) {
        this.nationalPokedexNumbers = nationalPokedexNumbers;
    }

    public int getConvertedRetreatCost() {
        return convertedRetreatCost;
    }

    public void setConvertedRetreatCost(int convertedRetreatCost) {
        this.convertedRetreatCost = convertedRetreatCost;
    }

    public String[] getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(String[] retreatCost) {
        this.retreatCost = retreatCost;
    }

    public String[] getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String[] getResistances() {
        return resistances;
    }

    public void setResistances(String[] resistances) {
        this.resistances = resistances;
    }

    public String[] getAttacks() {
        return attacks;
    }

    public void setAttacks(String[] attacks) {
        this.attacks = attacks;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(String[] subTypes) {
        this.subTypes = subTypes;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(String evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getFlavourText() {
        return flavourText;
    }

    public void setFlavourText(String flavourText) {
        this.flavourText = flavourText;
    }

    public PokemonCard() {
    }

    public PokemonCard(int id, String name, int countReaction, int level, int hp, int nationalPokedexNumbers, int convertedRetreatCost, String[] retreatCost, String[] weaknesses, String[] resistances, String[] attacks, String[] abilities, String[] types, String[] subTypes, String evolvesFrom, String set, String artist, String rarity, String flavourText) {
        this.id = id;
        this.name = name;
        this.countReaction = countReaction;
        this.level = level;
        this.hp = hp;
        this.nationalPokedexNumbers = nationalPokedexNumbers;
        this.convertedRetreatCost = convertedRetreatCost;
        this.retreatCost = retreatCost;
        this.weaknesses = weaknesses;
        this.resistances = resistances;
        this.attacks = attacks;
        this.abilities = abilities;
        this.types = types;
        this.subTypes = subTypes;
        this.evolvesFrom = evolvesFrom;
        this.set = set;
        this.artist = artist;
        this.rarity = rarity;
        this.flavourText = flavourText;
    }
}
