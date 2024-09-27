package nl.novi.TechItEasy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WallBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    public WallBracket() {
    }

    public WallBracket(String size, Boolean adjustable, String name, Double price) {
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }
}
