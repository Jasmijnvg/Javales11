package nl.novi.TechItEasy.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TelevisionController {

    @GetMapping("/televisions")
    public ResponseEntity<List<Map<String, Object>>> getAllTelevisions() {
        List<Map<String, Object>> televisions = List.of();
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable long id) {
        return ResponseEntity.ok("television");
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String televisionName) {
        String televisionName = "Samsung";
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(name).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String televisionName) {
        return ResponseEntity.noContent();
    }

    @DeleteMapping("television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        return ResponseEntity.noContent();
    }



}
