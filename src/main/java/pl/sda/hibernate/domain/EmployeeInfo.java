package pl.sda.hibernate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
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

    public EmployeeInfo() {
    }

    public EmployeeInfo(LocalDate hiredDate, Long daysSinceHired, String managerName) {
        this.hiredDate = hiredDate;
        this.daysSinceHired = daysSinceHired;
        this.managerName = managerName;
    }

    @PostLoad
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
