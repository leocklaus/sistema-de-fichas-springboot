package lck.fichasloja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientInputDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal amount;

}
