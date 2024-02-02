package pl.dc4b.cardirectory.services;

import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.entities.Car;

import javax.inject.Inject;
import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarDao carDao;

    @Inject
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }
    @Override
    public List<Car> getAllCars() {
        return this.carDao.getAll();
    }
}
