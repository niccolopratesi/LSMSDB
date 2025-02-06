package it.unipi.CardsGallery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("Card")
public class CardNode {
    @Id
    //@GeneratedValue
    private String id;

    private String name;
    private String TCG; //enum?????
}
