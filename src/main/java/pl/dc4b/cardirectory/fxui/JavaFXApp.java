package pl.dc4b.cardirectory.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.dc4b.cardirectory.config.AppComponent;
import pl.dc4b.cardirectory.config.DaggerAppComponent;

import java.io.IOException;

public class JavaFXApp extends Application {

    private static AppComponent appComponent;
    public static FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) {

        appComponent = DaggerAppComponent.create();

        loader = new FXMLLoader(getClass().getResource("/fxui/LayoutComponent.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Car Inventory App");
        primaryStage.setScene(scene);

        // Get the controller from the loader and call inject
        //CarListController controller = loader.getController();
        //appComponent.inject(controller);
        LayoutController controller = loader.getController();
        appComponent.inject(controller);

        // Initialize the controller or perform additional setup if needed
        controller.initialize();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
