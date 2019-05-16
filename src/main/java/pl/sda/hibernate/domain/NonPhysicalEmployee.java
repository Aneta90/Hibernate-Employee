package pl.sda.hibernate.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

@Data
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

    public NonPhysicalEmployee() {
    }

    public NonPhysicalEmployee(String name, String surname, Double salary, Adress adress) {
        super(name, surname, salary, adress);
    }


    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getExtraSalary() {
        return extraSalary;
    }

    public void setExtraSalary(Double extraSalary) {
        this.extraSalary = extraSalary;
    }
}
