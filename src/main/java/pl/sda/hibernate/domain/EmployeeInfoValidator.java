package pl.sda.hibernate.domain;
import lombok.*;
import pl.sda.hibernate.pl.sda.hibernate.impl.AfterCompanyWasCreated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@AfterCompanyWasCreated
public class EmployeeInfoValidator {


    @Id
    @GeneratedValue
    private Long employeeInfoId;

    @Column(nullable = false)
    private LocalDate hiredDate;

    @Transient
    private Long daysSinceHired;

    public void calculateDaysSinceHired(){
        this.daysSinceHired = ChronoUnit.DAYS.between(hiredDate,LocalDate.now());
        System.out.println(daysSinceHired);
    }



}
