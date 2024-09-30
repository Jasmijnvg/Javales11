package nl.novi.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Integer refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;

    @OneToOne
    @JoinColumn(name="remote_controller_id")
    private RemoteController remoteController;

    @ManyToOne
    @JoinColumn(name="ci_module_id")
    private CIModule ciModule;

    @ManyToMany(mappedBy = "televisions")
    private List<WallBracket> wallBrackets = new ArrayList<>();

}


