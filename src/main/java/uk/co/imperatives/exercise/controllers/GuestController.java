package uk.co.imperatives.exercise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.co.imperatives.exercise.domain.dtos.GuestDto;
import uk.co.imperatives.exercise.domain.wrapers.GuestWrapper;
import uk.co.imperatives.exercise.services.GuestService;

@RestController
@RequestMapping("/guest")
@AllArgsConstructor
@Tag(name = "Guest API")
public class GuestController {

    private final GuestService guestService;

    @Operation(summary = "Set the guest in one table")
    @PutMapping("/{name}")
    public GuestDto guestArrive(@PathVariable(value="name") String name,
                                @RequestBody  int accompanying_guests){
        return guestService.updateGuestTable(name, accompanying_guests);
    }

    @Operation(summary = "Remove Guest from Table")
    @DeleteMapping("/{name}")
    public String removeGuest(@PathVariable(value="name") String name){
        return guestService.removeGuest(name);
    }

    @Operation(summary = "Get a list of all the Guests")
    @GetMapping
    public GuestWrapper getAllGuests(){
        return guestService.getAllGuests();
    }
}
