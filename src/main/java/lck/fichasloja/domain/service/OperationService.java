package lck.fichasloja.domain.service;

import lck.fichasloja.api.dto.OperationDTO;
import lck.fichasloja.domain.exception.ResourceNotFound;
import lck.fichasloja.domain.model.Operation;
import lck.fichasloja.domain.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository repository;

    public List<OperationDTO> getAll(){
        List<Operation> operations = repository.findAll();
        return operations.stream()
                .map(OperationDTO::new)
                .toList();
    }

    public OperationDTO findById(Long id){
        Operation operation = returnOperationIfExistsOrThrowsAnException(id);
        return new OperationDTO(operation);
    }

    public OperationDTO save(OperationDTO dto){
     Operation operation = new Operation(dto);
     operation = repository.save(operation);
     return new OperationDTO(operation);
    }

    public OperationDTO update(OperationDTO dto, Long id){
        Operation operation = returnOperationIfExistsOrThrowsAnException(id);
        operation = copyFromOperationDTOToOperation(dto, operation);
        operation = repository.save(operation);
        return new OperationDTO(operation);
    }

    private Operation returnOperationIfExistsOrThrowsAnException(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFound(id));
    }

    private Operation copyFromOperationDTOToOperation(OperationDTO dto, Operation operation){
        operation.setDescription(dto.getDescription());
        return operation;
    }

}
