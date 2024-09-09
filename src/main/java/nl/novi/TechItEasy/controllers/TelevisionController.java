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
@RequestMapping("/api")
public class TelevisionController {

    List<String> televisions = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<List<String>> getAllTelevisions() {
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok(televisions.get(id));
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String televisionName) {
        televisions.add(televisionName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(televisions.size()-1).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String televisionName) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }



}
