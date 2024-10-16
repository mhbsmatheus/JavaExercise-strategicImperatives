package uk.co.imperatives.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.co.imperatives.exercise.domain.models.GuestModel;
import uk.co.imperatives.exercise.domain.models.TableModel;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {
    Optional<TableModel> findByTableNumber(int tableNumber);

    TableModel findByGuest(GuestModel guest);

    @Query("SELECT sum(e.availableSeats) from TableModel e")
    int numberOfEmptySeats();
}
