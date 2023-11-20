package fr.unilasalle.flight.api.beans;
@Entity
@Table(name="flights")

@Getter
@Setter
@NoArgsConstructor
public class flights extends PanacheEntityBase {

    @id
    private Long id;
    @NotBlank(message="Le numero de vol ne peut pas etre vide")
    @Column(nullable=false)
    private  Long number;

    @NotBlank(message="Le nom de famille ne peux pas etre vide")
    @Column(nullable=false)
    private String firstname;

    @NotBlank(message="L'address mail ne peut pas etre vide'")
    @Column(nullable=false)
    private String email_address;
}