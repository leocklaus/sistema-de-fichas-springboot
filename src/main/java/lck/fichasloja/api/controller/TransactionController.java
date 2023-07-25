package lck.fichasloja.api.controller;

import jakarta.validation.Valid;
import lck.fichasloja.api.ResourceURIHelper;
import lck.fichasloja.api.dto.TransactionDTO;
import lck.fichasloja.api.dto.TransactionInputDTO;
import lck.fichasloja.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAll(){
        List<TransactionDTO> transactions = service.getAll();
        return ResponseEntity
                .ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable Long id){
        TransactionDTO transaction = service.findById(id);
        return ResponseEntity
                .ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> saveTransaction(@Valid @RequestBody TransactionInputDTO transactionDTO){
        TransactionDTO transaction = service.save(transactionDTO);
        URI uri = ResourceURIHelper.getResourceURI(transaction.getId());
        return ResponseEntity
                .created(uri)
                .body(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(
            @Valid @RequestBody TransactionInputDTO transactionDTO,
            @PathVariable Long id
    ){
        TransactionDTO transaction = service.update(transactionDTO, id);
        return ResponseEntity
                .ok(transaction);
    }

}
