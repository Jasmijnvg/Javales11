package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.TelevisionOutputDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping()
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok(televisionService.getTelevisionById(id));
    }

    @PostMapping()
    public ResponseEntity<TelevisionOutputDto> addTelevision(@RequestBody TelevisionInputDto television) {
        TelevisionOutputDto t = televisionService.saveTelevision(television);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.id).toUri();
        return ResponseEntity.created(location).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTelevision(@PathVariable int id, @RequestBody TelevisionInputDto television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable int id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cimodule/{cimoduleid}")
    public ResponseEntity<Void> addCimodule(@PathVariable int id, @PathVariable int cimoduleid) {
        televisionService.assignCIModuleToTelevision(id, cimoduleid);
        return ResponseEntity.ok().build();
    }




}
