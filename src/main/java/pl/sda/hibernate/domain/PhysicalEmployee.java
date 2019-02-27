package pl.sda.hibernate.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class PhysicalEmployee extends Employee{

    @Transient
    private Double totalSalary;
    private Double salary;
    private Double extraSalary;

    @PostLoad
    public void calculateSalaryOfPhysicalEmployee(){
        this.totalSalary = salary + extraSalary;
    }
}
