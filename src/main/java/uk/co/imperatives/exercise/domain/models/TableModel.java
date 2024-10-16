package uk.co.imperatives.exercise.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TABLES")
public class TableModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idTable;
    private int tableNumber;
    private int capacity;
    private int availableSeats;

    @OneToOne
    @JoinColumn(name = "id_guest")
    private GuestModel guest;

}
