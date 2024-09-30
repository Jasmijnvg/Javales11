package nl.novi.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class WallBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "television_wall_bracket",
            joinColumns = @JoinColumn(name = "wall_bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "television_id")
            )
    private List<Television> televisions = new ArrayList<>();
//    private Set<Television> televisions = new HashSet<>();

//    List<Television> televisions;

}
