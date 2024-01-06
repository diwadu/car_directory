import dagger.internal.DaggerGenerated;
import pl.dc4b.cardirectory.dao.CalcDao;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.entities.Brand;
import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarColor;

import java.util.Comparator;
import java.util.List;
import dagger.*;

public class App {
    public static void main(String[] args) {

        AppComponent component = DaggerAppComponent.create();
        CalcDao calcDao = component.calcDao();
        calcDao.sayHello();

        CarDao carDao = new CarDao();

        // Insert a new car
        Car car = new Car();
        car.setBrand(Brand.BMW);
        car.setModel("M3");
        car.setProductionYear(2022);
        car.setColor(CarColor.Black);
        car.setVin("ABC123");

        carDao.create(car);

        // Retrieve all cars
        List<Car> allCars = carDao.getAll();
        for (Car carItem : allCars) {
            System.out.println(carItem.getId() + ": " + carItem.getBrand() + " " + carItem.getModel() + " (" + carItem.getProductionYear() + ")");
        }

        Car lastCar = allCars.stream()
                .sorted(Comparator.comparing(Car::getCreatedAt).reversed())
                .findFirst()
                .get();


        // Update a car
        Car carToUpdate = (Car) carDao.read(lastCar.getId());
        carToUpdate.setModel("Corolla");
        carDao.update(carToUpdate);

        // Retrieve the updated car
        Car updatedCar = (Car) carDao.read(carToUpdate.getId());
        System.out.println("Updated Car: " + updatedCar.getId() + ": " + updatedCar.getBrand() + " " + updatedCar.getModel() + " (" + updatedCar.getProductionYear() + ")");

        // Delete a car
        carDao.delete(updatedCar.getId());

        // Verify the deletion
        Car deletedCar = (Car) carDao.read(updatedCar.getId());
        System.out.println("Is Car Deleted: " + (deletedCar == null));
    }


}