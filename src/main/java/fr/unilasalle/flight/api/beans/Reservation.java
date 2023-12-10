package fr.unilasalle.flight.api.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence_in_java_code")
    @SequenceGenerator(name = "reservation_sequence_in_java_code", sequenceName = "reservation_sequence_in_database", allocationSize = 1)

    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;


    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;



}
