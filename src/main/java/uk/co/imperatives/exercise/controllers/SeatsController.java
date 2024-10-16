package uk.co.imperatives.exercise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.imperatives.exercise.domain.dtos.SeatsDto;
import uk.co.imperatives.exercise.services.TableService;

@RestController
@AllArgsConstructor
@Tag(name = "Seats API")
public class SeatsController {

    private final TableService tableService;

    @Operation(summary = "Count number of empty seats")
    @GetMapping("/seats_empty")
    public SeatsDto getNumberOfSeatsEmpty(){
        try{
            int numberSeats = tableService.getNumberOfSeatsEmpty();
            return SeatsDto.builder().seats_empty(numberSeats).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
