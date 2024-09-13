package nl.novi.TechItEasy.controllers;

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

    List<String> televisions = new ArrayList<>();

    @GetMapping()
    public ResponseEntity<List<String>> getAllTelevisions() {
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok(televisions.get(id));
    }

    @PostMapping()
    public ResponseEntity<Object> addTelevision(@RequestBody String televisionName) {
        televisions.add(televisionName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(televisions.size()-1).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String televisionName) {
        televisions.set(id, televisionName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisions.remove(id);
        return ResponseEntity.noContent().build();
    }



}
