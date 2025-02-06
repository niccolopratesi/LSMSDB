package it.unipi.CardsGallery.model;


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
    private int level;
    private int atk;
    private int def;
    private int countReaction;
    private String[] typeLine;
    private String[] printings;
}
