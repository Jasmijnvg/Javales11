package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.dtos.TelevisionOutputDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionService {


    private final TelevisionRepository televisionRepository;
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository=televisionRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions() {
        return televisionRepository.findAll()
                .stream()
                .map(TelevisionService::toTelevisionDto)
                .collect(Collectors.toList());
    }

    public TelevisionOutputDto getTelevisionById(int id) {
        Television t = televisionRepository.findById(id).get();
        return toTelevisionDto(t);
    }

    public TelevisionOutputDto saveTelevision(TelevisionInputDto television) {
        Television t = toTelevision(television);
        televisionRepository.save(t);
        return toTelevisionDto(t);
    }

    public void deleteTelevision(int id) {
        televisionRepository.deleteById(id);
    }

    public TelevisionOutputDto updateTelevision(int id, TelevisionInputDto newTv) {
        Television t = toTelevision(newTv);
        t.setId(id);
        televisionRepository.save(t);


        return toTelevisionDto(t);
    }

    public static TelevisionOutputDto toTelevisionDto(Television television){
        var dto = new TelevisionOutputDto();

        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }

    public Television toTelevision(TelevisionInputDto dto) {
        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }

}
