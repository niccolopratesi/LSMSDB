package it.unipi.CardsGallery.model.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String title;
    private String text;
    private String cardId;
    private TCG type;
    private Date creationDate;
}
