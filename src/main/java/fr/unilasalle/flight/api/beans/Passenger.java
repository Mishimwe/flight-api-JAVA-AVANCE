package fr.unilasalle.flight.api.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
public class Passenger extends PanacheEntityBase {

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_sequence_in_java_code")
    @SequenceGenerator(name = "passenger_sequence_in_java_code", sequenceName = "passenger_sequence_in_database", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String firstname;

    @Column( nullable = false,unique = true)
    private String email_address;


    // One-to-Many relationship: a passenger can have multiple reservations
    /*@OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    @JsonIgnoreProperties("passenger")
    private List<Reservation> reservations;*/


    public void setEmailAddress(String emailAddress) {
    }
}
