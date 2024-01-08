package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.*;
import pl.dc4b.cardirectory.helpers.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDaoImpl<T extends Car> extends CrudDaoImpl<Car> implements CarDao {

    private static final String SELECT_ALL_QUERY =
            " SELECT " +
            "   cars.id, cars.brand, cars.model, cars.productionYear, cars.color, cars.vin, cars.createdAt, cars.updatedAt, " +
            "   contractors.id AS contractor_id, contractors.firstName, contractors.lastName, contractors.email, contractors.phone, contractors.type, contractors.createdAt, contractors.updatedAt " +
            " FROM cars " +
            "   LEFT JOIN cars_2_contractors ON cars.id = cars_2_contractors.car_id " +
            "   LEFT JOIN contractors ON cars_2_contractors.contractor_id = contractors.id";
    public CarDaoImpl() {
        super(Car.class);
    }
    @Override
    public String getTableName() {
        return "cars";
    }

    @Override
    protected void setParametersForInsert(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setString(1, car.getBrand().toString());
        preparedStatement.setString(2, car.getModel());
        preparedStatement.setInt(3, car.getProductionYear());
        preparedStatement.setString(4, car.getColor().toString());
        preparedStatement.setString(5, car.getVin());
        preparedStatement.setObject(6, LocalDateTime.now());
        preparedStatement.setObject(7, LocalDateTime.now());
    }

    @Override
    protected void setParametersForUpdate(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setString(1, car.getBrand().toString());
        preparedStatement.setString(2, car.getModel());
        preparedStatement.setInt(3, car.getProductionYear());
        preparedStatement.setString(4, car.getColor().toString());
        preparedStatement.setString(5, car.getVin());
        preparedStatement.setObject(6, LocalDateTime.now());
    }

    @Override
    protected Car mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setBrand(CarBrand.valueOf(resultSet.getString("brand")));
        car.setModel(resultSet.getString("model"));
        car.setProductionYear(resultSet.getInt("productionYear"));
        car.setColor(CarColor.valueOf(resultSet.getString("color")));
        car.setVin(resultSet.getString("vin"));
        car.setCreatedAt(resultSet.getObject("createdAt", LocalDateTime.class));
        car.setUpdatedAt(resultSet.getObject("updatedAt", LocalDateTime.class));

        return car;
    }

    @Override
    public List<Car> findByBrand(String brand) {
        return getAll().stream().filter(x -> x.getBrand().equals(brand)).toList();
    }

    @Override
    public List<Car> getAll() {
        Map<Integer, Car> carMap = new HashMap<>();

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int carId = resultSet.getInt("id");
                    Car car = carMap.computeIfAbsent(carId, k -> {
                        try {
                            return mapResultSetToEntity(resultSet);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    // Contractor details
                    int contractorId = resultSet.getInt("contractor_id");
                    if (contractorId != 0) {
                        Contractor contractor = new Contractor();
                        contractor.setId(contractorId);
                        contractor.setFirstName(resultSet.getString("firstName"));
                        contractor.setLastName(resultSet.getString("lastName"));
                        contractor.setEmail(resultSet.getString("email"));
                        contractor.setPhone(resultSet.getString("phone"));
                        contractor.setType(ContractorType.valueOf(resultSet.getString("type")));
                        contractor.setCreatedAt(resultSet.getObject("createdAt", LocalDateTime.class));
                        contractor.setUpdatedAt(resultSet.getObject("updatedAt", LocalDateTime.class));
                        car.getContractors().add(contractor);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(carMap.values());
    }

}