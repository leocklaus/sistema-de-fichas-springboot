package lck.fichasloja.domain.service;

import lck.fichasloja.api.dto.CashierDTO;
import lck.fichasloja.domain.exception.ResourceNotFound;
import lck.fichasloja.domain.model.Cashier;
import lck.fichasloja.domain.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierService {

    @Autowired
    private CashierRepository repository;

    public List<CashierDTO> getAll() {
        List<Cashier> cashiers = repository.findAll();
        return cashiers.stream()
                .map(CashierDTO::new)
                .toList();
    }

    public CashierDTO findById(Long id) {
        Cashier cashier = returnACashierIfIdExistsOrThrowAnError(id);
        return new CashierDTO(cashier);
    }

    public CashierDTO save(CashierDTO cashierDTO) {
        Cashier cashier = new Cashier(cashierDTO);
        cashier = repository.save(cashier);
        return new CashierDTO(cashier);
    }

    public CashierDTO update(CashierDTO cashierDTO, Long id){
        Cashier cashier = returnACashierIfIdExistsOrThrowAnError(id);
        cashier = copyFromDTOToCashier(cashier, cashierDTO);
        cashier = repository.save(cashier);
        return new CashierDTO(cashier);
    }

    private Cashier returnACashierIfIdExistsOrThrowAnError(Long id ) {
        Cashier cashier = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFound(id));
        return cashier;
    }

    private Cashier copyFromDTOToCashier(Cashier cashier, CashierDTO dto){
        cashier.setName(dto.getName());
        return cashier;
    }


}
