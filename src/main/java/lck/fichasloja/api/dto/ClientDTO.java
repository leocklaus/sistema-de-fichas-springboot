package lck.fichasloja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lck.fichasloja.domain.model.Client;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClientDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.amount = client.getAmount();
        this.createdAt = client.getCreatedAt();
    }
}
