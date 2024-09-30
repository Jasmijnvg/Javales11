package nl.novi.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CIModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;
    private String type;
    private Double price;

    @OneToMany(mappedBy = "ciModule")
    private List<Television> televisions;
}
