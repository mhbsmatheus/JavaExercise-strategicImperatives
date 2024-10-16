package uk.co.imperatives.exercise.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_GUESTS")
public class GuestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idGuest;
    private String name;
    private int partySize;
    private Date timeArrived;

    @OneToOne
    @JoinColumn(name = "id_table")
    private TableModel table;

}
