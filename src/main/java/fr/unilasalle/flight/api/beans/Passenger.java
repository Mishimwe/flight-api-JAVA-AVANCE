package fr.unilasalle.flight.api.beans;
@Entity
@Table(name="passengers")

@Getter
@Setter
@NoArgsConstructor
public class passengers extends PanacheEntityBase {

    @id
    private Long id;

    @NotBlank(message="Le prénom ne peux pas etre vide")
    @Column(nullable=false)
    private  String surname;

    @NotBlank(message="Le nom de famille ne peux pas etre vide")
    @Column(nullable=false)
    private String firstname;

    @NotBlank(message="L'address mail ne peut pas etre vide'")
    @Column(nullable=false)
    private String email_address;
}