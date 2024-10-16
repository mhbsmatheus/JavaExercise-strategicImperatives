package uk.co.imperatives.exercise.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TableDto {

    @NotBlank
    private int tableNumber;
    @NotBlank
    private int capacity;
    @JsonIgnore
    private int availableSeats;
}
