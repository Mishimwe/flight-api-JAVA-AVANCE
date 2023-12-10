package fr.unilasalle.flight.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "planes")
@Getter
@Setter
@NoArgsConstructor
public class Plane extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planes_sequence_in_java_code")
    @SequenceGenerator(name = "planes_sequence_in_java_code", sequenceName = "planes_sequence_in_database", allocationSize = 1)
    private Long id;

    @NotBlank(message = "le fabricant ne doit pas être vide")
    @Column(nullable = false)
    private String operator;

    @Column(nullable = false)
    @NotBlank(message = "le modèle ne doit pas être vide")
    private String model;

    @NotBlank(message = "l'immatriculation ne doit pas être vide")
    @Column(nullable = false,unique = true)
    private String immatriculation;

    @NotNull(message = "la capacité doit être fournie")
    @Min(value = 2, message = "La capacité de l'avion doit être supérieure à 2")
    @Column(nullable = false)
    private Integer capacity;


    @JsonManagedReference
    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights;


}
