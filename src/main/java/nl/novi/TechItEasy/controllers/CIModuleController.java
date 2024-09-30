package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.CIModuleInputDto;
import nl.novi.TechItEasy.dtos.CIModuleOutputDto;
import nl.novi.TechItEasy.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/CIModule")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping()
    public ResponseEntity<List<CIModuleOutputDto>> getAllCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleOutputDto> getCIModule(@PathVariable int id) {
        return ResponseEntity.ok(ciModuleService.getCIModuleById(id));
    }

    @PostMapping()
    public ResponseEntity<CIModuleOutputDto> addCIModule(@RequestBody CIModuleInputDto television) {
        CIModuleOutputDto t = ciModuleService.saveCIModule(television);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.id).toUri();
        return ResponseEntity.created(location).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCIModule(@PathVariable int id, @RequestBody CIModuleInputDto television) {
        ciModuleService.updateCIModule(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCIModule(@PathVariable int id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }



}
