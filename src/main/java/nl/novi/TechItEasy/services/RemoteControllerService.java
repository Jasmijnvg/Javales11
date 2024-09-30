package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.dtos.RemoteControllerInputDto;
import nl.novi.TechItEasy.dtos.RemoteControllerOutputDto;
import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.RemoteController;
import nl.novi.TechItEasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerOutputDto> getAllRemoteControllers(){
        return remoteControllerRepository.findAll()
                .stream()
                .map((x) -> toRemoteControllerDto(x))
                .collect(Collectors.toList());
    }

    public RemoteControllerOutputDto getRemoteControllerById(long id){
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);
        if(optionalRemoteController.isPresent()){
            RemoteController t = optionalRemoteController.get();
            return toRemoteControllerDto(t);
        }
        else{
            throw new RecordNotFoundException(id + " not found");
        }
    }

    public RemoteControllerOutputDto saveRemoteController(RemoteControllerInputDto remoteController){
        RemoteController t = toRemoteController(remoteController);
        RemoteController savedRemoteController = remoteControllerRepository.save(t);
        return toRemoteControllerDto(savedRemoteController);
    }

    public void deleteRemoteController(long id){
        if(remoteControllerRepository.existsById(id)){
            remoteControllerRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException(id + " not found");
        }
    }

    public RemoteControllerOutputDto updateRemoteController(long id, RemoteControllerInputDto updatedRemoteController){
        RemoteController t = toRemoteController(updatedRemoteController);
        t.setId(id);
        RemoteController updated = remoteControllerRepository.save(t);
        return toRemoteControllerDto(updated);
    }

    public static RemoteControllerOutputDto toRemoteControllerDto(RemoteController remoteController){
        var dto = new RemoteControllerOutputDto();

       dto.setId(remoteController.getId());
       dto.setCompatibleWith(remoteController.getCompatibleWith());
       dto.setBatteryType(remoteController.getBatteryType());
       dto.setName(remoteController.getName());
       dto.setBrand(remoteController.getBrand());
       dto.setPrice(remoteController.getPrice());
       dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }

    public RemoteController toRemoteController(RemoteControllerInputDto dto){
        var remoteController = new RemoteController();

        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }
}
