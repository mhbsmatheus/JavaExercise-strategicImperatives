package uk.co.imperatives.exercise.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.imperatives.exercise.domain.dtos.GuestListDto;
import uk.co.imperatives.exercise.domain.exceptions.GuestListException;
import uk.co.imperatives.exercise.domain.models.GuestModel;
import uk.co.imperatives.exercise.domain.models.TableModel;
import uk.co.imperatives.exercise.domain.wrapers.GuestListWrapper;
import uk.co.imperatives.exercise.repositories.GuestRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestListService {

    private final GuestRepository guestRepository;
    private final TableService tableService;

    public GuestListWrapper getGuestList() {
        try{
            List<GuestModel> guestModelList = guestRepository.findAll();

            List<GuestListDto> guestListDtos = guestModelList.stream().map( guest ->
                            GuestListDto.builder()
                                    .name(guest.getName())
                                    .table(guest.getTable().getTableNumber())
                                    .accompanying_guests(guest.getPartySize())
                                    .build())
                    .toList();

            return new GuestListWrapper(guestListDtos);
        } catch (Exception e) {
            throw new GuestListException("Error for get the guest list", e);
        }
    }

    public String saveGuestList(String guestName, GuestListDto guestListDto) {

        try{
            TableModel table = tableService.getTableByNumber(guestListDto.getTable());

            if (table.getAvailableSeats() < guestListDto.getAccompanying_guests()) {
                throw new GuestListException("Number of Guest exceeds table capacity");
            }

            validateGuestName(guestName);

            GuestModel guest = GuestModel.builder()
                    .table(table)
                    .name(guestName)
                    .partySize(guestListDto.getAccompanying_guests())
                    .build();

            guestRepository.save(guest);

            tableService.updateTableGuest(table, guest);

            return "Guest Set on List";
        } catch (Exception e) {
            throw new GuestListException("Error", e);
        }
    }

    private void validateGuestName(String guestName) {
        GuestModel guest = guestRepository.findByName(guestName);
        if (guest != null) {
            throw new GuestListException("Can`t have 2 guest with the same name");
        }
    }
}
