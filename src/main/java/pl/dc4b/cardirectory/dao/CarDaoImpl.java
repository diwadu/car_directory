package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarBrand;
import pl.dc4b.cardirectory.entities.CarColor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class CarDaoImpl<T extends Car> extends CrudDaoImpl<Car> implements CarDao {

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
}