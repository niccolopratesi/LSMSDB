package it.unipi.CardsGallery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.Date;

@Data
@Document(collection = "Post")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private String text;
    private int authorId;
    private int cardId;
    private String TCG; //enum????
    private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTCG() {
        return TCG;
    }

    public void setTCG(String TCG) {
        this.TCG = TCG;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Post() {
    }

    public Post(int id, String text, int authorId, int cardId, String TCG, Date creationDate) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.cardId = cardId;
        this.TCG = TCG;
        this.creationDate = creationDate;
    }
}
