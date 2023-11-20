package fr.unilasalle.flight.api.beans;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="planes")

@Getter
@Setter
@NoArgsConstructor
public class Avion extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name="planes_sequence_in_java_code",sequenceName="planes_sequence_in_database",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="planes_sequence_in_database")
    private Long id;
    @Column(nullable=false)
    private  String operator;
    @Column(nullable=false)
    private  String model;
    @Column(nullable=false)

    private String immatriculation;
    @Column(nullable=false)
    private Integer capacity;
}