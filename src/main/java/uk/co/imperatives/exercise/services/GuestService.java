package uk.co.imperatives.exercise.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.imperatives.exercise.domain.dtos.GuestDto;
import uk.co.imperatives.exercise.domain.exceptions.GuestException;
import uk.co.imperatives.exercise.domain.models.GuestModel;
import uk.co.imperatives.exercise.domain.wrapers.GuestWrapper;
import uk.co.imperatives.exercise.repositories.GuestRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final TableService tableService;

    @Transactional
    public GuestDto updateGuestTable(String name, int accompanyingGuests) {

        try {
            GuestModel guest = guestRepository.findByName(name);

            validateTableSize(guest, accompanyingGuests);

            guest.setPartySize(accompanyingGuests);
            guest.setTimeArrived(new Date());
            guest.getTable().setAvailableSeats(guest.getTable().getAvailableSeats() - accompanyingGuests);

            guestRepository.save(guest);

            return GuestDto.builder().name(guest.getName()).build();
        } catch (Exception e) {
            throw new GuestException("No seats Available", e);
        }
    }

    private void validateTableSize(GuestModel guest, int accompanyingGuests) {
        if (guest.getTable().getAvailableSeats() < accompanyingGuests) {
            throw new GuestException("Table don`t have enough seats");
        }
    }

    public String removeGuest(String name) {
        try{
            GuestModel guest = guestRepository.findByName(name);
            if (guest == null){
                throw new GuestException("Guest not found");
            }

            tableService.removeGuest(guest);

            guestRepository.delete(guest);
            return name+" has left";
        } catch (Exception e) {
            throw new GuestException("Erro removing Guest",e);
        }
    }

    public GuestWrapper getAllGuests() {
        try{
            List<GuestModel> guests = guestRepository.findAll();

            List<GuestDto> guestDtos = guests.stream().map(guest ->
                            GuestDto.builder()
                                    .name(guest.getName())
                                    .accompanying_guests(guest.getPartySize())
                                    .time_arrived(guest.getTimeArrived().toString())
                                    .build())
                    .toList();
            return new GuestWrapper(guestDtos);
        } catch (Exception e) {
            throw new GuestException("Something wrong happen", e);
        }
    }
}
