package it.unipi.CardsGallery.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("User")
@NoArgsConstructor
@AllArgsConstructor
public class UserNode {
    @Id
    private Long id;

    @Version
    private Long version;

    private String username;

    public UserNode(String username) {
        this.username = username;
    }
}
