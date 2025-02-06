package it.unipi.CardsGallery.model.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.List;

@Data
@Document(collection = "CardList")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CardList {
    @Id
    @GeneratedValue
    private String id;

    private String name;
    private boolean status;
    private List<Card> cards;
    private String userId;
    private String username;

    public CardList(String name, boolean status, List<Card> cards, String userId, String username) {
        this.name = name;
        this.status = status;
        this.cards = cards;
        this.userId = userId;
        this.username = username;
    }
}
