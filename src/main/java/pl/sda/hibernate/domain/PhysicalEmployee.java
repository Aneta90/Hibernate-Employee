package pl.sda.hibernate.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Data
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

    public PhysicalEmployee() {
    }

    public PhysicalEmployee(String name, String surname, Double salary, Adress adress, Double totalSalary, Double salary1, Double extraSalary) {
        super(name, surname, salary, adress);
        this.totalSalary = totalSalary;
        this.salary = salary1;
        this.extraSalary = extraSalary;
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
