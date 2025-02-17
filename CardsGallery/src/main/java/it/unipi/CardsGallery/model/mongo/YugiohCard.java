package it.unipi.CardsGallery.model.mongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Document(collection = "Yugioh")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class YugiohCard {
    @Id @GeneratedValue
    private String id;

    private String name;
    private String desc;
    private String race;
    private String attribute;
    private String type;
    private Integer level;
    private Integer atk;
    private Integer def;
    private String[] typeLine;
    private String[] printings;

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

    public void updateCard(YugiohCard card) {
        if(card.getName() != null) {
            this.name = card.getName();
        }
        if(card.getDesc() != null) {
            this.desc = card.getDesc();
        }
        if(card.getRace() != null) {
            this.race = card.getRace();
        }
        if(card.getAttribute() != null) {
            this.attribute = card.getAttribute();
        }
        if(card.getType() != null) {
            this.type = card.getType();
        }
        if(card.getLevel() != null) {
            this.level = card.getLevel();
        }
        if(card.getAtk() != null) {
            this.atk = card.getAtk();
        }
        if(card.getDef() != null) {
            this.def = card.getDef();
        }
        if(card.getTypeLine() != null) {
            this.typeLine = card.getTypeLine();
        }
        if(card.getPrintings() != null) {
            this.printings = card.getPrintings();
        }
    }
}
