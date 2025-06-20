package it.unipi.CardsGallery.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Node("Post")
@NoArgsConstructor
@AllArgsConstructor
public class PostNode {
    @Id
    private Long id;

    private String title;

    @Version
    private Long version;

    public PostNode(String title) {
        this.title = title;
    }
}
