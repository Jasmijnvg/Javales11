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
public class CIModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String name;
    private String type;
    private Double price;

    public CIModule() {
    }

    public CIModule(String name, String type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
