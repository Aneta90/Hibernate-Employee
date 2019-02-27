package pl.sda.hibernate.domain;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Department {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Employee> employeeList;



}
