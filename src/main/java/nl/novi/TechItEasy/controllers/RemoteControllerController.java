package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.RemoteControllerInputDto;
import nl.novi.TechItEasy.dtos.RemoteControllerOutputDto;
import nl.novi.TechItEasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/RemoteController")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping()
    public ResponseEntity<List<RemoteControllerOutputDto>> getAllRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerOutputDto> getRemoteController(@PathVariable int id) {
        return ResponseEntity.ok(remoteControllerService.getRemoteControllerById(id));
    }

    @PostMapping()
    public ResponseEntity<RemoteControllerOutputDto> addRemoteController(@RequestBody RemoteControllerInputDto remoteController) {
        RemoteControllerOutputDto t = remoteControllerService.saveRemoteController(remoteController);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
        return ResponseEntity.created(location).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRemoteController(@PathVariable int id, @RequestBody RemoteControllerInputDto remoteController) {
        remoteControllerService.updateRemoteController(id, remoteController);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemoteController(@PathVariable int id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }
}

