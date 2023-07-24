package lck.fichasloja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lck.fichasloja.domain.model.Cashier;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CashierDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    public CashierDTO(Cashier cashier){
        this.id = cashier.getId();
        this.name = cashier.getName();
    }

}
