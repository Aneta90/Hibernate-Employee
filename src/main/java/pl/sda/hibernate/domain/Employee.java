package pl.sda.hibernate.domain;
import lombok.*;
import javax.persistence.*;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private Double salary;

    @OneToOne
    Adress adress;

}
