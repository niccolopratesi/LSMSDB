package it.unipi.CardsGallery.model.mongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Magic")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class MagicCard {
    @Id @GeneratedValue
    private String id;

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
    private Float manaValue;
    private Boolean isFunny;

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

    public void updateCard(MagicCard card) {
        if(card.getName() != null) {
            this.name = card.getName();
        }
        if(card.getText() != null) {
            this.text = card.getText();
        }
        if(card.getType() != null) {
            this.type = card.getType();
        }
        if(card.getLayout() != null) {
            this.layout = card.getLayout();
        }
        if(card.getManaCost() != null) {
            this.manaCost = card.getManaCost();
        }
        if(card.getPower() != null) {
            this.power = card.getPower();
        }
        if(card.getThoughness() != null) {
            this.thoughness = card.getThoughness();
        }
        if(card.getFirstPrinting() != null) {
            this.firstPrinting = card.getFirstPrinting();
        }
        if(card.getPrintings() != null) {
            this.printings = card.getPrintings();
        }
        if(card.getColors() != null) {
            this.colors = card.getColors();
        }
        if(card.getColorIdentity() != null) {
            this.colorIdentity = card.getColorIdentity();
        }
        if(card.getManaValue() != null) {
            this.manaValue = card.getManaValue();
        }
        if(card.getIsFunny() != null) {
            this.isFunny = card.getIsFunny();
        }
    }
}
