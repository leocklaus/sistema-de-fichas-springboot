package lck.fichasloja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lck.fichasloja.domain.model.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperationDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    public OperationDTO(Operation operation){
        this.id = operation.getId();
        this.description = operation.getDescription();
    }
}
