package pl.dc4b.cardirectory.fxui;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.dc4b.cardirectory.entities.*;
import pl.dc4b.cardirectory.services.CarService;

import javax.inject.Inject;
import java.util.ArrayList;
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

    //private final CarService carService;

   /* @Inject
    public CarListController(CarService carService){
        this.carService = carService;
    }*/

    private final ObservableList<Car> allCars = FXCollections.observableArrayList();


    public void initialize() {
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

        // Example: Populate the table with sample data
        allCars.add(new Car(CarBrand.Audi, "A4", 2022, CarColor.Yellow, "ABC123456789", createContractors()));
        allCars.add(new Car(CarBrand.BMW, "X5", 2021, CarColor.Green, "XYZ987654321", createContractors()));
        allCars.add(new Car(CarBrand.Ford, "Mustang", 2023, CarColor.Black, "DEF456123789", createContractors()));

        carTableView.setItems(allCars);

        //var cars = carService.getAllCars();

        // Example: Configure search functionality
        configureSearch();
        configureReset();
    }

    private List<Contractor> createContractors() {
        List<Contractor> contractors = new ArrayList<>();
        contractors.add(new Contractor("John", "Doe", "john@example.com", "07000 737383", ContractorType.ELectrician, null));
        contractors.add(new Contractor("John", "Doe", "john@example.com", "07000 737383", ContractorType.Owner, null));
        // Add more contractors if needed
        return contractors;
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
