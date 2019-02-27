package pl.sda.hibernate.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class NonPhysicalEmployee extends Employee {

    @Transient
    private Double totalSalary;
    @Min(2000)
    private Double salary;
    private Double extraSalary;

    @PostLoad
    public void calculateSalaryOfNonPhysicalEmployee(){
        this.totalSalary = salary + extraSalary;

    }


}
