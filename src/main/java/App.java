import pl.dc4b.cardirectory.config.AppComponent;
import pl.dc4b.cardirectory.config.DaggerAppComponent;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarBrand;
import pl.dc4b.cardirectory.entities.CarColor;
import pl.dc4b.cardirectory.fxui.JavaFXApp;
import pl.dc4b.cardirectory.helpers.DbHelper;
import pl.dc4b.cardirectory.services.CarService;

import java.util.Comparator;
import java.util.List;

public class App {
    public  static AppComponent appComponent;

    public static void main(String[] args) {
        setupDI();
        //setupDb();

        CarService carService = appComponent.carService();
        //doCrudStuff(carDao);
        var cars = carService.getAllCars();

        JavaFXApp.main(args);
    }

    private static void setupDI() {
        appComponent = DaggerAppComponent.create();
    }

    private static void setupDb() {
        DbHelper.executeSqlScript("create_db.sql");
    }

    /**
     * Helper method
     * @param carDao - CarDAO instance
     */
    private static void doCrudStuff(CarDao carDao) {
        // Insert a new car
        Car car = new Car();
        car.setBrand(CarBrand.BMW);
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
        Car carToUpdate = (Car) carDao.getById(lastCar.getId());
        carToUpdate.setModel("Corolla");
        carDao.update(carToUpdate);

        // Retrieve the updated car
        Car updatedCar = (Car) carDao.getById(carToUpdate.getId());
        System.out.println("Updated Car: " + updatedCar.getId() + ": " + updatedCar.getBrand() + " " + updatedCar.getModel() + " (" + updatedCar.getProductionYear() + ")");

        // Delete a car
        //carDao.delete(updatedCar.getId());

        // Verify the deletion
        //Car deletedCar = (Car) carDao.read(updatedCar.getId());
        //System.out.println("Is Car Deleted: " + (deletedCar == null));
    }


}