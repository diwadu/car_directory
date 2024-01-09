package pl.dc4b.cardirectory.fxui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.dc4b.cardirectory.config.DaggerAppComponent;
import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarBrand;
import pl.dc4b.cardirectory.entities.CarColor;
import pl.dc4b.cardirectory.services.CarService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarListController {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button resetButton;

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableColumn<Car, CarBrand> brandColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Integer> productionYearColumn;

    @FXML
    private TableColumn<Car, CarColor> colorColumn;

    @FXML
    private TableColumn<Car, String> vinColumn;

    @FXML
    private TableColumn<Car, Integer> numberOfContractorsColumn;

    private CarService carService;

    @Inject
    public void injectDependencies(CarService carService) {
        this.carService = carService;
    }

    private final ObservableList<Car> allCars = FXCollections.observableArrayList();


    public void initialize() {

        DaggerAppComponent.create().inject(this);

        // Initialize the table columns with property values
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        productionYearColumn.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));

        numberOfContractorsColumn.setCellValueFactory(param -> {
            Car car = param.getValue();
            IntegerBinding sizeBinding = Bindings.size(FXCollections.observableArrayList(car.getContractors()));
            return sizeBinding.asObject();
        });


        List<Car> cars = carService.getAllCars();
        allCars.setAll(FXCollections.observableArrayList(cars));

        carTableView.setItems(allCars);

        configureSearch();
        configureReset();
    }

    private void configureSearch() {
        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText().toLowerCase();
            List<Car> filteredCars = allCars.stream()
                    .filter(car -> car.getBrand().name().toLowerCase().contains(searchText)
                            || car.getModel().toLowerCase().contains(searchText)
                            || String.valueOf(car.getProductionYear()).contains(searchText)
                            || car.getColor().name().toLowerCase().contains(searchText)
                            || car.getVin().toLowerCase().contains(searchText)
                            || String.valueOf(car.getContractors().size()).contains(searchText))
                    .collect(Collectors.toList());

            carTableView.setItems(FXCollections.observableArrayList(filteredCars));
        });
    }

    private void configureReset() {
        resetButton.setOnAction(event -> {
            searchTextField.clear();
            List<Car> filteredCars = allCars.stream()
                    .collect(Collectors.toList());

            carTableView.setItems(FXCollections.observableArrayList(filteredCars));
        });
    }
}
