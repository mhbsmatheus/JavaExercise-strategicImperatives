package uk.co.imperatives.exercise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.co.imperatives.exercise.domain.dtos.GuestListDto;
import uk.co.imperatives.exercise.domain.wrapers.GuestListWrapper;
import uk.co.imperatives.exercise.services.GuestListService;

@RestController
@RequestMapping("/guest_list")
@AllArgsConstructor
@Tag(name = "Guest List API")
public class GuestListController {

    private final GuestListService guestListService;

    @Operation(summary = "Get a list of all guest reservations")
    @GetMapping
    public GuestListWrapper getAllGuestList(){
        return guestListService.getGuestList();
    }

    @Operation(summary = "Set the reservation for the guest")
    @PostMapping("/{name}")
    public String saveTable(@PathVariable(value="name") String guestName,
                                @RequestBody @Valid GuestListDto guestListDto){
        return guestListService.saveGuestList(guestName, guestListDto);
    }
}
