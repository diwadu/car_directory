package pl.dc4b.cardirectory.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.dc4b.cardirectory.services.CarService;

import java.io.IOException;

public class JavaFXApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxui/MainApp.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Car Inventory App");
        primaryStage.setScene(scene);

        // Get the controller from the loader
        CarListController controller = loader.getController();

        // Initialize the controller or perform additional setup if needed
        controller.initialize();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }
}
