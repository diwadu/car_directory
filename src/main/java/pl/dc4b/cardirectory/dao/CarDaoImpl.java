package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.Brand;
import pl.dc4b.cardirectory.entities.Car;
import pl.dc4b.cardirectory.entities.CarColor;
import pl.dc4b.cardirectory.helpers.DbHelper;

import java.sql.*;
import java.time.LocalDateTime;

public class CarDaoImpl<T extends Car> extends CrudDaoImpl<Car> {

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
        car.setBrand(Brand.valueOf(resultSet.getString("brand")));
        car.setModel(resultSet.getString("model"));
        car.setProductionYear(resultSet.getInt("productionYear"));
        car.setColor(CarColor.valueOf(resultSet.getString("color")));
        car.setVin(resultSet.getString("vin"));
        car.setCreatedAt(resultSet.getObject("createdAt", LocalDateTime.class));
        car.setUpdatedAt(resultSet.getObject("updatedAt", LocalDateTime.class));

        return car;
    }

    public void create(T entity) {
        try (Connection connection = DbHelper.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(super.buildInsertQuery())) {
                setParametersForInsert(preparedStatement, entity);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (Statement stmt = connection.createStatement()) {
                        try (ResultSet resultSet = stmt.executeQuery("SELECT LAST_INSERT_ROWID()")) {
                            if (resultSet.next()) {
                                entity.setId(resultSet.getInt(1));
                            } else {
                                throw new SQLException("Failed to retrieve the last inserted row ID.");
                            }
                        }
                    }
                } else {
                    throw new SQLException("Creating entity failed, no rows affected.");
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}