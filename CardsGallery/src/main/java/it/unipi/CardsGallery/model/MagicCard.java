package it.unipi.CardsGallery.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Magic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MagicCard {
    @Id @GeneratedValue
    private int id;

    private String name;
    private String text;
    private String type;
    private String layout;
    private String manaCost;
    private String power;
    private String thoughness;
    private String firstPrinting;
    private String[] printings;
    private String[] colors;
    private String[] colorIdentity;
    private float manaValue;
    private boolean isFunny;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getThoughness() {
        return thoughness;
    }

    public void setThoughness(String thoughness) {
        this.thoughness = thoughness;
    }

    public String getFirstPrinting() {
        return firstPrinting;
    }

    public void setFirstPrinting(String firstPrinting) {
        this.firstPrinting = firstPrinting;
    }

    public String[] getPrintings() {
        return printings;
    }

    public void setPrintings(String[] printings) {
        this.printings = printings;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String[] getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(String[] colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public float getManaValue() {
        return manaValue;
    }

    public void setManaValue(float manaValue) {
        this.manaValue = manaValue;
    }

    public boolean isFunny() {
        return isFunny;
    }

    public void setFunny(boolean funny) {
        isFunny = funny;
    }

    public MagicCard() {
    }

    public MagicCard(int id, String name, String text, String type, String layout, String manaCost, String power, String thoughness, String firstPrinting, String[] printings, String[] colors, String[] colorIdentity, float manaValue, boolean isFunny) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.type = type;
        this.layout = layout;
        this.manaCost = manaCost;
        this.power = power;
        this.thoughness = thoughness;
        this.firstPrinting = firstPrinting;
        this.printings = printings;
        this.colors = colors;
        this.colorIdentity = colorIdentity;
        this.manaValue = manaValue;
        this.isFunny = isFunny;
    }
}
