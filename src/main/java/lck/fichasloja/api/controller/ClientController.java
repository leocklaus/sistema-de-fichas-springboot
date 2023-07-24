package lck.fichasloja.api.controller;

import jakarta.validation.Valid;
import lck.fichasloja.api.ResourceURIHelper;
import lck.fichasloja.api.dto.ClientDTO;
import lck.fichasloja.api.dto.ClientInputDTO;
import lck.fichasloja.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){
        List<ClientDTO> clients = service.getAll();
        return ResponseEntity
                .ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO client = service.findById(id);
        return ResponseEntity
                .ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientInputDTO clientDto){
        ClientDTO client = service.save(clientDto);

        URI uri = ResourceURIHelper.getResourceURI(client.getId());

        return ResponseEntity
                .created(uri)
                .body(client);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> updateClient(
            @Valid @RequestBody ClientInputDTO clientDto, @PathVariable Long id
    ){
        ClientDTO client = service.update(clientDto, id);

        return ResponseEntity
                .ok(client);
    }

}
