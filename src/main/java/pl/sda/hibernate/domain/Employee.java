package pl.sda.hibernate.domain;
import lombok.*;
import javax.persistence.*;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private Double salary;

    public Employee() {
    }

    public Employee(String name, String surname, Double salary, Adress adress) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.adress = adress;
    }

    @OneToOne
    Adress adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
