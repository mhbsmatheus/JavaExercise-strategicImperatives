package uk.co.imperatives.exercise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.imperatives.exercise.domain.models.TableModel;
import uk.co.imperatives.exercise.domain.dtos.TableDto;
import uk.co.imperatives.exercise.services.TableService;

@AllArgsConstructor
@RestController
@RequestMapping("/table")
@Tag(name = "Table API")
public class TableController {

    private final TableService tableService;

    @Operation(summary = "Get the information about one table")
    @GetMapping("/{tableNumber}")
    public Object getOneTable(@PathVariable(value="tableNumber") int tableNumber){
        return tableService.getOneTable(tableNumber);
    }

    @Operation(summary = "Set a Table to be booked")
    @PostMapping
    public TableModel saveTable(@RequestBody @Valid TableDto tableDto){
        return tableService.saveTable(tableDto);
    }
}
