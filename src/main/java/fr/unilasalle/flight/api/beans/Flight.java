package fr.unilasalle.flight.api.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence_in_java_code")
    @SequenceGenerator(name = "flight_sequence_in_java_code", sequenceName = "flight_sequence_in_database", allocationSize = 1)
    private Long id;

    @NotNull(message = "Le numéro de vol ne peut pas être vide")
    @Column(nullable = false,unique = true)
    private String number;


    @NotNull(message = "L'origine ne peut pas être vide")
    @Column(nullable = false)
    private String origin;


    @NotNull(message = "La destination ne peut pas être vide")
    @Column(nullable = false)
    private String destination;


    @NotNull(message = "La date de départ ne peut pas être vide")
    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;


    @NotNull(message = "L'heure de départ ne peut pas être vide")
    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;


    @NotNull(message = "La date d'arrivée ne peut pas être vide")
    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;


    @NotNull(message = "L'heure d'arrivée ne peut pas être vide")
    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reservation> reservations;



}
