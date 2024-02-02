package pl.dc4b.cardirectory.config;

import dagger.Module;
import dagger.Provides;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.dao.CarDaoImpl;
import pl.dc4b.cardirectory.services.CarService;
import pl.dc4b.cardirectory.services.CarServiceImpl;
import pl.dc4b.cardirectory.textui.CarTextUIManager;
import pl.dc4b.cardirectory.textui.CarTextUIManagerImpl;

@Module
public class AppModule {
    @Provides
    static CarDao provideCarDao(){
        return new CarDaoImpl<>();
    }

    @Provides
    static CarService provideCarService(CarDao carDao){
        return new CarServiceImpl(carDao);
    }

    @Provides
    static CarTextUIManager provideDependencies(CarDao carDao){
        return new CarTextUIManagerImpl(carDao);
    }
}
