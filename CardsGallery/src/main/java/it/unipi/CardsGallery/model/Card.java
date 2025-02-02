package it.unipi.CardsGallery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Card {
    @Id
    private int id;
    private String name;
    private String TCG; //enum????
    private String printingSet;

    //dati utili per le query
    private int pokedexNumber;

    private List<String> colors;

    private String type;
    private String attribute;

    public String getName() {
        return name;
    }
    public String getTCG() {
        return TCG;
    }
    public int getId() {
        return id;
    }
    public String getPrintingSet() {
        return printingSet;
    }
    public int getPokedexNumber() {
        return pokedexNumber;
    }
    public List<String> getColors() {
        return colors;
    }
    public String getType() {
        return type;
    }
    public String getAttribute() {
        return attribute;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPrintingSet(String printingSet) {
        this.printingSet = printingSet;
    }
    public void setTCG(String TCG) {
        this.TCG = TCG;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }
    public void setColors(List<String> colors) {
        this.colors = colors;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Card() {
    }
    public Card(int id, String name, String TCG, int pokedexNumber, List<String> colors, String type, String attribute) {
        this.id = id;
        this.name = name;
        this.TCG = TCG;
        this.pokedexNumber = pokedexNumber;
        this.colors = colors;
        this.type = type;
        this.attribute = attribute;
    }
}
