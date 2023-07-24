package lck.fichasloja.domain.model;

import jakarta.persistence.*;
import lck.fichasloja.api.dto.TransactionInputDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_operation")
    private Operation operation;

    @Column(nullable = false)
    private BigDecimal prevAmount;

    @Column(nullable = false)
    private BigDecimal partialBallance;

    @CurrentTimestamp
    private LocalDateTime createdAt;

    public Transaction(TransactionInputDTO dto){
        this.id = dto.getId();
        this.description = dto.getDescription();
        this.operation = dto.getOperation();
        this.prevAmount = dto.getPrevAmount();
        this.partialBallance = dto.getPartialBallance();
    }
}
