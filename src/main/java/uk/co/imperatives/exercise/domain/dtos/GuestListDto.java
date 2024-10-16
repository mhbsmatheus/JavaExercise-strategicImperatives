package uk.co.imperatives.exercise.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuestListDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;
    @NotBlank
    private int table;
    @NotBlank
    private int accompanying_guests;

}
