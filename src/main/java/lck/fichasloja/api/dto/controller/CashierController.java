package lck.fichasloja.api.dto.controller;

import lck.fichasloja.api.dto.CashierDTO;
import lck.fichasloja.domain.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    @Autowired
    private CashierService service;

    public ResponseEntity<List<CashierDTO>> getAll(){
        List<CashierDTO> cashiers = service.getAll();
        return ResponseEntity
                .ok(cashiers);
    }

}
