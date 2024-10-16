package uk.co.imperatives.exercise.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.imperatives.exercise.domain.exceptions.TableException;
import uk.co.imperatives.exercise.domain.models.GuestModel;
import uk.co.imperatives.exercise.domain.models.TableModel;
import uk.co.imperatives.exercise.domain.dtos.TableDto;
import uk.co.imperatives.exercise.repositories.TableRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public TableModel saveTable(@Valid TableDto tableDto) {
        var tableModel = createTableFromRecord(tableDto);
        return tableRepository.save(tableModel);
    }

    public TableDto getOneTable(int tableNumber) {
        TableModel tableModel = getTableByNumber(tableNumber);
        return TableDto.builder()
                .tableNumber(tableModel.getTableNumber())
                .capacity(tableModel.getCapacity())
                .availableSeats(tableModel.getTableNumber())
                .build();
    }

    public TableModel getTableByNumber(int tableNumber) {
        Optional<TableModel> tableModel = tableRepository.findByTableNumber(tableNumber);
        if(tableModel.isEmpty()) {
            throw new TableException("Table not found.");
        }
        return tableModel.get();
    }

    private static TableModel createTableFromRecord(TableDto tableDto) {
        return TableModel.builder()
                .tableNumber(tableDto.getTableNumber())
                .capacity(tableDto.getCapacity())
                .availableSeats(tableDto.getCapacity())
                .build();
    }

    public void updateTableGuest(TableModel table, GuestModel guest) {
        table.setGuest(guest);

        tableRepository.save(table);
    }

    public void removeGuest(GuestModel guest) {
        try {
            TableModel tableModel = tableRepository.findByGuest(guest);
            if (tableModel == null) {
                throw new TableException("Table not found for the guest");
            }
            tableModel.setAvailableSeats(tableModel.getCapacity());
            tableModel.setGuest(null);
            tableRepository.save(tableModel);
        } catch (Exception e) {
            throw new TableException("Error", e);
        }
    }

    public int getNumberOfSeatsEmpty() {
        return tableRepository.numberOfEmptySeats();
    }
}
