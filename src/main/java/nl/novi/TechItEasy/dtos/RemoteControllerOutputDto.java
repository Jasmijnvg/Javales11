package nl.novi.TechItEasy.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteControllerOutputDto {
    public Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    public RemoteControllerOutputDto() {
    }
}
