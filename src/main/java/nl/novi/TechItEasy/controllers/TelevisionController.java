package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.services.TelevisionService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping()
    public ResponseEntity<List<Television>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok(televisionService.getTelevisionById(id));
    }

    @PostMapping()
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        Television t = televisionService.saveTelevision(television);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.id).toUri();
        return ResponseEntity.created(location).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable int id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }



}
