package pl.dc4b.cardirectory.fxui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.dc4b.cardirectory.config.DaggerAppComponent;

import java.io.IOException;

public class LayoutController{
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button resetButton;

    @FXML
    private CarListController carListController;

    public void initialize() {
        DaggerAppComponent.create().inject(this);

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxui/CarListComponent.fxml"));
        //carListController = loader.getController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxui/CarListComponent.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        carListController = (CarListController) loader.getController();

        resetButton.setOnAction(event -> {
            searchTextField.clear();
            carListController.handleReset();
        });

        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText().toLowerCase();
            carListController.handleSearch(searchText);
        });


    }
}
