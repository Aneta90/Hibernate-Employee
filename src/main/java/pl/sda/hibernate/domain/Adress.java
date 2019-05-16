package pl.sda.hibernate.domain;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String zipCode;
    private int streetNumber;

    public Adress() {
    }

    public Adress(String city, String street, String zipCode, int streetNumber, Employee employee) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
        this.employee = employee;
    }

    @OneToOne (mappedBy = "adress", orphanRemoval = true, cascade = CascadeType.ALL)
    Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", streetNumber=" + streetNumber +
                ", employee=" + employee +
                '}';
    }
}
