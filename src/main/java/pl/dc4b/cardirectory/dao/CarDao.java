package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.Car;

import java.util.List;

public interface CarDao extends CrudDao<Car> {
    List<Car> findByBrand(String brand);

    List<Car> getAll();
}
