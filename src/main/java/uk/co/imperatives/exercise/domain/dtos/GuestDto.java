package uk.co.imperatives.exercise.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuestDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer accompanying_guests;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time_arrived;
}
