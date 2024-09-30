package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.dtos.WallBracketInputDto;
import nl.novi.TechItEasy.dtos.WallBracketOutputDto;
import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.WallBracket;
import nl.novi.TechItEasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracketOutputDto> getAllWallBrackets(){
        return wallBracketRepository.findAll()
                .stream()
                .map((x) -> toWallBracketDto(x))
                .collect(Collectors.toList());
    }

    public WallBracketOutputDto getWallBracketById(long id){
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);
        if(optionalWallBracket.isPresent()){
            WallBracket t = optionalWallBracket.get();
            return toWallBracketDto(t);
        }
        else{
            throw new RecordNotFoundException(id + " not found");
        }
    }

    public WallBracketOutputDto saveWallBracket(WallBracketInputDto wallBracket){
        WallBracket t = toWallBracket(wallBracket);
        WallBracket savedWallBracket = wallBracketRepository.save(t);
        return toWallBracketDto(savedWallBracket);
    }

    public void deleteWallBracket(long id){
        if(wallBracketRepository.existsById(id)){
            wallBracketRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException(id + " not found");
        }
    }

    public WallBracketOutputDto updateWallBracket(long id, WallBracketInputDto updatedWallBracket){
        WallBracket t = toWallBracket(updatedWallBracket);
        t.setId(id);
        WallBracket updated = wallBracketRepository.save(t);
        return toWallBracketDto(updated);
    }

    public static WallBracketOutputDto toWallBracketDto(WallBracket wallBracket){
        var dto = new WallBracketOutputDto();

        dto.setId(wallBracket.getId());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());

        return dto;
    }

    public WallBracket toWallBracket(WallBracketInputDto dto){
        var wallBracket = new WallBracket();

        wallBracket.setSize(dto.getSize());
        wallBracket.setAdjustable(dto.getAdjustable());
        wallBracket.setName(dto.getName());
        wallBracket.setPrice(dto.getPrice());

        return wallBracket;
    }
}
