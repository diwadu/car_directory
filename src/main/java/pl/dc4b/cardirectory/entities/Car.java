package pl.dc4b.cardirectory.entities;

import pl.dc4b.cardirectory.dao.IgnoreInSql;

import java.util.ArrayList;
import java.util.List;

public class Car extends BaseEntity {
    private CarBrand brand;
    private String model;
    private int productionYear;
    private CarColor color;
    private String vin;

    @IgnoreInSql
    private List<Contractor> contractors = new ArrayList<>();

    public Car() {
    }

    public Car(
            CarBrand brand, String model, int productionYear, CarColor color,
            String vin, List<Contractor> contractors) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.color = color;
        this.vin = vin;
        this.contractors = new ArrayList<>();
        this.contractors = contractors;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand carBrand) {
        this.brand = carBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }
}


