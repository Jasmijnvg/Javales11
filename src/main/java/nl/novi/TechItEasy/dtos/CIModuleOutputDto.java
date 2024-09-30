package nl.novi.TechItEasy.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CIModuleOutputDto {
    public Long id;

    private String name;
    private String type;
    private Double price;

    public CIModuleOutputDto() {
    }
}
