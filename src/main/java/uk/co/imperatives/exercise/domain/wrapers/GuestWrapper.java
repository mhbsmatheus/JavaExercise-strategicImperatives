package uk.co.imperatives.exercise.domain.wrapers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uk.co.imperatives.exercise.domain.dtos.GuestDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GuestWrapper {

    private List<GuestDto> guests;
}
