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
}
