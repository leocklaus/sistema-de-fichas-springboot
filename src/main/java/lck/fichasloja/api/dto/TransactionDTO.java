package lck.fichasloja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lck.fichasloja.domain.model.Operation;
import lck.fichasloja.domain.model.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private Operation operation;

    @NotNull
    private BigDecimal prevAmount;

    @NotNull
    private BigDecimal partialBallance;

    private LocalDateTime createdAt;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.operation = transaction.getOperation();
        this.prevAmount = transaction.getPrevAmount();
        this.partialBallance = transaction.getPartialBallance();
        this.createdAt = transaction.getCreatedAt();
    }

}
