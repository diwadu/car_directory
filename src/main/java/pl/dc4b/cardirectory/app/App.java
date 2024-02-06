package pl.dc4b.cardirectory.app;

import pl.dc4b.cardirectory.config.AppComponent;
import pl.dc4b.cardirectory.config.DaggerAppComponent;
import pl.dc4b.cardirectory.helpers.DbHelper;
import pl.dc4b.cardirectory.services.CarService;

import java.util.Scanner;

public class App {
    public static AppComponent appComponent;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupDI();
        //setupDb();

        CarService carService = appComponent.carService();
        var cars = carService.getAllCars();

        showMainMenu();
        //JavaFXApp.main(args);
    }

    public static void showMainMenu () {
        while (true) {
            System.out.println("------------------------------");
            System.out.println("1. Cars");
            System.out.println("2. Exit");


            System.out.print("-> Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    appComponent.carTextUIManager().showMainMenu();
                    break;
                case 2:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void setupDI() {
        appComponent = DaggerAppComponent.create();
    }

    private static void setupDb() {
        DbHelper.executeSqlScript("create_db.sql");
    }
}