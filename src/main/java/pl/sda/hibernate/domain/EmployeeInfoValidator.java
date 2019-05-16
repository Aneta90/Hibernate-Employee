package pl.sda.hibernate.domain;
import lombok.*;
import pl.sda.hibernate.pl.sda.hibernate.impl.AfterCompanyWasCreated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AfterCompanyWasCreated
@Entity
public class EmployeeInfoValidator {


    @Id
    @GeneratedValue
    private Long employeeInfoId;

    @Column(nullable = false)
    private LocalDate hiredDate;

    @Transient
    private Long daysSinceHired;

    public EmployeeInfoValidator() {
    }

    public EmployeeInfoValidator(LocalDate hiredDate, Long daysSinceHired) {
        this.hiredDate = hiredDate;
        this.daysSinceHired = daysSinceHired;
    }

    public void calculateDaysSinceHired(){
        this.daysSinceHired = ChronoUnit.DAYS.between(hiredDate,LocalDate.now());
        System.out.println(daysSinceHired);
    }

    public Long getEmployeeInfoId() {
        return employeeInfoId;
    }

    public void setEmployeeInfoId(Long employeeInfoId) {
        this.employeeInfoId = employeeInfoId;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Long getDaysSinceHired() {
        return daysSinceHired;
    }

    public void setDaysSinceHired(Long daysSinceHired) {
        this.daysSinceHired = daysSinceHired;
    }
}
