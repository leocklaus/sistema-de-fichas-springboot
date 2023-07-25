package lck.fichasloja.api.controller;

import jakarta.validation.Valid;
import lck.fichasloja.api.ResourceURIHelper;
import lck.fichasloja.api.dto.OperationDTO;
import lck.fichasloja.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class OperationController {

    @Autowired
    private OperationService service;

    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAll(){
        List<OperationDTO> operations = service.getAll();
        return ResponseEntity
                .ok(operations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> findById(@PathVariable Long id){
        OperationDTO operation = service.findById(id);
        return ResponseEntity
                .ok(operation);
    }

    @PostMapping
    public ResponseEntity<OperationDTO> saveOperation(@Valid @RequestBody OperationDTO operationDTO){
        operationDTO = service.save(operationDTO);

        URI uri = ResourceURIHelper.getResourceURI(operationDTO.getId());

        return ResponseEntity
                .created(uri)
                .body(operationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationDTO> updateOperation(
            @Valid @RequestBody OperationDTO operationDTO, @PathVariable Long id
    ){

        operationDTO = service.update(operationDTO, id);

        return ResponseEntity
                .ok(operationDTO);
    }

}
