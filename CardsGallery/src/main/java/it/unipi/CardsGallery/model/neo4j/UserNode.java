package it.unipi.CardsGallery.model.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("User")
public class UserNode {
    @Id
    private String id;

    private String username;
}
