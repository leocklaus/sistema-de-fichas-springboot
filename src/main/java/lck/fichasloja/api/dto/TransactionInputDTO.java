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
public class TransactionInputDTO {

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

}
