package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.WallBracketInputDto;
import nl.novi.TechItEasy.dtos.WallBracketOutputDto;
import nl.novi.TechItEasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/WallBracket")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping()
    public ResponseEntity<List<WallBracketOutputDto>> getAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketOutputDto> getWallBracket(@PathVariable int id) {
        return ResponseEntity.ok(wallBracketService.getWallBracketById(id));
    }

    @PostMapping()
    public ResponseEntity<WallBracketOutputDto> addWallBracket(@RequestBody WallBracketInputDto wallBracket) {
        WallBracketOutputDto t = wallBracketService.saveWallBracket(wallBracket);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.id).toUri();
        return ResponseEntity.created(location).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWallBracket(@PathVariable int id, @RequestBody WallBracketInputDto wallBracket) {
        wallBracketService.updateWallBracket(id, wallBracket);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallBracket(@PathVariable int id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

}

