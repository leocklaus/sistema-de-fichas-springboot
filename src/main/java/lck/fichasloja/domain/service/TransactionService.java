package lck.fichasloja.domain.service;

import lck.fichasloja.api.dto.TransactionDTO;
import lck.fichasloja.api.dto.TransactionInputDTO;
import lck.fichasloja.domain.exception.ResourceNotFound;
import lck.fichasloja.domain.model.Transaction;
import lck.fichasloja.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<TransactionDTO> getAll(){
        List<Transaction> transactions = repository.findAll();
        return transactions.stream()
                .map(TransactionDTO::new)
                .toList();
    }

    public TransactionDTO findById(Long id){
        Transaction transaction = returnsTransactionIfExistsOrThrowsAnException(id);
        return new TransactionDTO(transaction);
    }

    public TransactionDTO save(TransactionInputDTO dto){
        Transaction transaction = new Transaction(dto);
        transaction = repository.save(transaction);
        return new TransactionDTO(transaction);
    }

    public TransactionDTO update(TransactionInputDTO dto, Long id){
        Transaction transaction = returnsTransactionIfExistsOrThrowsAnException(id);
        transaction = copyFromTransactionDTOToTransaction(dto, transaction);
        transaction = repository.save(transaction);
        return new TransactionDTO(transaction);
    }

    private Transaction returnsTransactionIfExistsOrThrowsAnException(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFound(id));
    }

    private Transaction copyFromTransactionDTOToTransaction(TransactionInputDTO dto, Transaction transaction){
        transaction.setDescription(dto.getDescription());
        transaction.setOperation(dto.getOperation());
        transaction.setPrevAmount(dto.getPrevAmount());
        transaction.setPartialBallance(dto.getPartialBallance());
        return transaction;
    }

}
