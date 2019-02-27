package pl.sda.hibernate.domain;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String zipCode;
    private int streetNumber;

    @OneToOne (mappedBy = "adress", orphanRemoval = true, cascade = CascadeType.ALL)
    Employee employee;

}
