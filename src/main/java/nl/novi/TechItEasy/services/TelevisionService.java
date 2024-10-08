package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.dtos.TelevisionOutputDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.CIModule;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.CIModuleRepository;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelevisionService {


    private final TelevisionRepository televisionRepository;
    private final CIModuleRepository ciModuleRepository;

    public TelevisionService(TelevisionRepository televisionRepository, CIModuleRepository ciModuleRepository) {
        this.televisionRepository=televisionRepository;
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions() {
        return televisionRepository.findAll()
                .stream()
                .map((x)->toTelevisionDto(x))
                .collect(Collectors.toList());
    }

    public TelevisionOutputDto getTelevisionById(int id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isPresent()) {
            Television t = optionalTelevision.get();
            return toTelevisionDto(t);
        }
        else{
            throw new RecordNotFoundException(id+" not found");
        }
    }

    public TelevisionOutputDto saveTelevision(TelevisionInputDto television) {
        Television t = toTelevision(television);
        Television tv =televisionRepository.save(t);
        return toTelevisionDto(tv);
    }

    public void deleteTelevision(int id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException(id+" not found");
        }
    }

    public TelevisionOutputDto updateTelevision(int id, TelevisionInputDto newTv) {
        Television t = toTelevision(newTv);
        t.setId(id);
        Television tv =televisionRepository.save(t);
        return toTelevisionDto(tv);
    }

    public Television assignCIModuleToTelevision(int televisionId, long ciModuleId){
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleId);
        if (optionalCIModule.isEmpty()) {
            throw new RecordNotFoundException(televisionId + " not found");
        }

        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException(televisionId + " not found");
        }

        CIModule ciModule = optionalCIModule.get();
        Television television = optionalTelevision.get();

        ciModule.getTelevisions().add(television);
        television.setCiModule(ciModule);

        ciModuleRepository.save(ciModule);
        televisionRepository.save(television);

        return television;
    }



    public static TelevisionOutputDto toTelevisionDto(Television television){
        var dto = new TelevisionOutputDto();

        dto.setId(television.getId());
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
        if(television.getCiModule()!=null) {
            dto.setCiModule(CIModuleService.toCIModuleDto(television.getCiModule()));
        }

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
