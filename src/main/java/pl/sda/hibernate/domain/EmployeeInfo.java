package pl.sda.hibernate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@NamedQueries({ @NamedQuery(name = "employeeInfo.findByHiredDate", query = "from EmployeeInfo emp where emp.hiredDate=:date"),
                @NamedQuery(name = "employeeInfo.findByName", query = "from EmployeeInfo emp where emp.managerName=:name")})
public class EmployeeInfo {

    @Id
    @GeneratedValue
    private Long employeeInfoId;

    @Column(nullable = false)
    private LocalDate hiredDate;

    @Transient
    private Long daysSinceHired;

    private String managerName;

    @PostLoad
    public void calculateDaysSinceHired(){
        this.daysSinceHired = ChronoUnit.DAYS.between(hiredDate,LocalDate.now());
        System.out.println(daysSinceHired);
    }


}
