package pl.dc4b.cardirectory.textui;

import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarBrand;
import pl.dc4b.cardirectory.entities.CarColor;

import javax.inject.Inject;
import java.util.Scanner;

public class CarTextUIManagerImpl extends BaseTextUIManager implements CarTextUIManager {
    private static final Scanner scanner = new Scanner(System.in);

    private final CarDao carDao;

    @Inject
    public CarTextUIManagerImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void showMainMenu() {
        while (true) {
            showMenuTitle("Cars - CRUD Operations:");
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Update Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Back to Main Menu");

            showEnterChoice();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    viewCars();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    //deleteCar();
                    break;
                case 5:
                    return; // Back to the main menu
                default:
                    showInvalidChoice();
            }
        }
    }

    private void addCar() {
        Car car = new Car();

        showMenuTitle("Enter Car Details:");

        System.out.print("Brand (Audi/BMW/Ford): ");
        car.setBrand(CarBrand.valueOf(scanner.nextLine()));

        System.out.print("Model: ");
        car.setModel(scanner.nextLine());

        System.out.print("Production Year: ");
        car.setProductionYear(scanner.nextInt());
        scanner.nextLine(); //

        System.out.print("Color (Black/Yellow/Green): ");
        car.setColor(CarColor.valueOf(scanner.nextLine()));

        System.out.print("VIN: ");
        car.setVin(scanner.nextLine());

        this.carDao.create(car);
        showSuccessMessage();
    }

    private void viewCars() {
        showTableHeader(new String[] {"id", "brand", "model", "year", "color", "vin", "createdAt", "updatedAt"});
        this.carDao.getAll().forEach(x->System.out.println(x.toString()));
    }

    private void DisplayCar(Car car){
        showTableHeader(new String[] {"id", "brand", "model", "year", "color", "vin", "createdAt", "updatedAt"});
        System.out.println(car.toString());
    }

    private void updateCar() {
        showMenuTitle("Enter the car ID to update: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car existingCar = carDao.getById(carId);

        if (existingCar == null) {
            showErrorMessage("Car not found with ID: " + carId);
            return;
        }

        showMenuTitle("Current Car Details:");
        DisplayCar(existingCar);

        // Now, let the user update the car details
        showMenuTitle("Enter new details:");

        System.out.print("Brand (Enter to keep current value: " + existingCar.getBrand() + "): ");
        String brand = scanner.nextLine().trim();
        existingCar.setBrand(brand.isEmpty() ? existingCar.getBrand() : CarBrand.valueOf(brand));

        System.out.print("Model (Enter to keep current value: " + existingCar.getModel() + "): ");
        String model = scanner.nextLine().trim();
        existingCar.setModel(model.isEmpty() ? existingCar.getModel() : model);

        System.out.print("Production Year (Enter to keep current value: " + existingCar.getProductionYear() + "): ");
        String productionYearInput = scanner.nextLine().trim();
        int productionYear = productionYearInput.isEmpty() ? existingCar.getProductionYear() : Integer.parseInt(productionYearInput);
        existingCar.setProductionYear(productionYear);

        System.out.print("Color (Enter to keep current value: " + existingCar.getColor() + "): ");
        String color = scanner.nextLine().trim();
        existingCar.setColor(color.isEmpty() ? existingCar.getColor() : CarColor.valueOf(color));

        System.out.print("VIN (Enter to keep current value: " + existingCar.getVin() + "): ");
        String vin = scanner.nextLine().trim();
        existingCar.setVin(vin.isEmpty() ? existingCar.getVin() : vin);

        // Update the car in the database
        carDao.update(existingCar);

        System.out.println("Car updated successfully!");
    }

}
