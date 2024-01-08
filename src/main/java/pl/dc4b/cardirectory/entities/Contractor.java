package pl.dc4b.cardirectory.entities;

import java.util.List;

public class Contractor extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private ContractorType type;
    private List<Car> cars;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public ContractorType getType() {
        return type;
    }
    public void setType(ContractorType type) {
        this.type = type;
    }
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Contractor(){

    }

    public Contractor(String firstName, String lastName,
                      String email, String phone,
                      ContractorType type, List<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.cars = cars;
    }
}
