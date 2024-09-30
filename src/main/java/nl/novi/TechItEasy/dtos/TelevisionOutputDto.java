package nl.novi.TechItEasy.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.novi.TechItEasy.models.WallBracket;

import java.util.List;

@Getter
@Setter
public class TelevisionOutputDto {

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
    public CIModuleOutputDto ciModule;
    public RemoteControllerOutputDto remoteController;
    private List<WallBracketOutputDto> wallbrackets;
//    public WallBracket wallBracket;

    public TelevisionOutputDto() {
    }
}
