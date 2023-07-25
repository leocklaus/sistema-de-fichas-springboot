package lck.fichasloja.api.controller;

import jakarta.validation.Valid;
import lck.fichasloja.api.ResourceURIHelper;
import lck.fichasloja.api.dto.CashierDTO;
import lck.fichasloja.domain.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    @Autowired
    private CashierService service;

    @GetMapping
    public ResponseEntity<List<CashierDTO>> getAll(){
        List<CashierDTO> cashiers = service.getAll();
        return ResponseEntity
                .ok(cashiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashierDTO> findById(@PathVariable Long id){
        CashierDTO cashier = service.findById(id);
        return ResponseEntity
                .ok(cashier);
    }

    @PostMapping
    public ResponseEntity<CashierDTO> addCashier(@Valid @RequestBody CashierDTO cashier){
        cashier = service.save(cashier);

        URI uri = ResourceURIHelper.getResourceURI(cashier.getId());

        return ResponseEntity
                .created(uri)
                .body(cashier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashierDTO> updateCashier(
            @Valid @RequestBody CashierDTO cashier, @RequestParam Long id
    ){
        cashier = service.update(cashier, id);
        return ResponseEntity
                .ok(cashier);
    }

}
