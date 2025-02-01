package it.unipi.CardsGallery.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Yugioh")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YugiohCard {
    @Id @GeneratedValue
    private int id;

    private String name;
    private String desc;
    private String race;
    private String attribute;
    private String type;
    private int level;
    private int atk;
    private int def;
    private int countReaction;
    private String[] typeLine;
    private String[] printings;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getCountReaction() {
        return countReaction;
    }

    public void setCountReaction(int countReaction) {
        this.countReaction = countReaction;
    }

    public String[] getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String[] typeLine) {
        this.typeLine = typeLine;
    }

    public String[] getPrintings() {
        return printings;
    }

    public void setPrintings(String[] printings) {
        this.printings = printings;
    }

    public YugiohCard() {
    }

    public YugiohCard(int id, String name, String desc, String race, String attribute, String type, int level, int atk, int def, int countReaction, String[] typeLine, String[] printings) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.race = race;
        this.attribute = attribute;
        this.type = type;
        this.level = level;
        this.atk = atk;
        this.def = def;
        this.countReaction = countReaction;
        this.typeLine = typeLine;
        this.printings = printings;
    }
}
