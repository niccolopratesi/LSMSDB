package it.unipi.CardsGallery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("Card")
public class CardNode {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String TCG; //enum?????

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

    public String getTCG() {
        return TCG;
    }

    public void setTCG(String TCG) {
        this.TCG = TCG;
    }

    public CardNode() {
    }

    public CardNode(int id, String name, String TCG) {
        this.id = id;
        this.name = name;
        this.TCG = TCG;
    }
}
