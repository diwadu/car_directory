package pl.dc4b.cardirectory.entities;

public class Car extends BaseEntity {
    private Brand brand;
    private String model;
    private int productionYear;
    private CarColor color;
    private String vin;

    public Car() {
        // Default constructor
    }

    public Car(Brand brand, String model, int productionYear, CarColor color, String vin) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.color = color;
        this.vin = vin;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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
}


