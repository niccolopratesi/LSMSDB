package it.unipi.CardsGallery.model.neo4j;

import it.unipi.CardsGallery.model.enums.TCG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.UUID;

@Data
@Node("Card")
@NoArgsConstructor
@AllArgsConstructor
public class CardNode {
    @Id
    private Long id;

    @Version
    private Long version;

    private String name;
    private TCG type;
    private String identifier;
}
