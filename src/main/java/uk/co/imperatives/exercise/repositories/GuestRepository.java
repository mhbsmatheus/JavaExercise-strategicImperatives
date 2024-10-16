package uk.co.imperatives.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.imperatives.exercise.domain.models.GuestModel;

@Repository
public interface GuestRepository extends JpaRepository<GuestModel, Long> {
    GuestModel findByName(String name);
}
