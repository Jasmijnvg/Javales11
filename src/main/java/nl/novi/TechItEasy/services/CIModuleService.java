package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.dtos.CIModuleInputDto;
import nl.novi.TechItEasy.dtos.CIModuleOutputDto;
import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.CIModule;
import nl.novi.TechItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;
    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModuleOutputDto> getAllCIModules(){
        return ciModuleRepository.findAll()
                .stream()
                .map((x)->toCIModuleDto(x))
                .collect(Collectors.toList());
    }

    public CIModuleOutputDto getCIModuleById(long id){
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if(optionalCIModule.isPresent()){
            CIModule t = optionalCIModule.get();
            return toCIModuleDto(t);
        }
        else{
            throw new RecordNotFoundException(id+" not found");
        }
    }

    public CIModuleOutputDto saveCIModule(CIModuleInputDto ciModule){
        CIModule t = toCIModule(ciModule);
        CIModule CI = ciModuleRepository.save(t);
        return toCIModuleDto(CI);
    }

    public void deleteCIModule(long id){
        if(ciModuleRepository.existsById(id)){
            ciModuleRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException(id+" not found");
        }
    }

    public CIModuleOutputDto updateCIModule(long id, CIModuleInputDto updatedciModule){
        CIModule t = toCIModule(updatedciModule);
        t.setId(id);
        CIModule CI = ciModuleRepository.save(t);
        return toCIModuleDto(CI);
    }

    public static CIModuleOutputDto toCIModuleDto(CIModule ciModule){
        var dto = new CIModuleOutputDto();

        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        return dto;
    }

    public CIModule toCIModule(CIModuleInputDto dto){
        var ciModule = new CIModule();

        ciModule.setName(dto.getName());
        ciModule.setType(dto.getType());
        ciModule.setPrice(dto.getPrice());

        return ciModule;

    }
}
