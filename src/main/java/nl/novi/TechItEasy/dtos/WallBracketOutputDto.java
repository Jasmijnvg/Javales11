package nl.novi.TechItEasy.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallBracketOutputDto {
    public Long id;

    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

}
