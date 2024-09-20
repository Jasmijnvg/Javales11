package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {


    private final TelevisionRepository televisionRepository;
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository=televisionRepository;
    }

    public List<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }

    public Television getTelevisionById(int id) {
        return televisionRepository.findById(id).get();
    }

    public Television saveTelevision(Television television) {
        Television t = televisionRepository.save(television);
        return t;
    }

    public void deleteTelevision(int id) {
        televisionRepository.deleteById(id);
    }

    public Television updateTelevision(int id, Television newTv) {

        newTv.setId(id);
        Television t = televisionRepository.save(newTv);


        return t;
    }

}
