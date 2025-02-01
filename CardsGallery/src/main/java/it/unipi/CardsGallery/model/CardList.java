package it.unipi.CardsGallery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.List;

@Data
@Document(collection = "List")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardList {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private boolean status;
    private List<Card> cards;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardList() {
    }

    public CardList(int id, String name, boolean status, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.cards = cards;
    }
}
