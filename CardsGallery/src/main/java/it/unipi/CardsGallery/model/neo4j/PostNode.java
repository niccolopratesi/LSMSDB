package it.unipi.CardsGallery.model.neo4j;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("Post")
public class PostNode {
    @Id
    private String id;

    private String title;
}
