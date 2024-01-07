import dagger.Module;
import dagger.Provides;
import pl.dc4b.cardirectory.dao.CalcDao;
import pl.dc4b.cardirectory.dao.CalcDaoImpl;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.dao.CarDaoImpl;
import pl.dc4b.cardirectory.entities.Car;

@Module
public class AppModule {
    @Provides
    static CalcDao provideMyService() {
        return new CalcDaoImpl();
    }
    @Provides
    static CarDao provideCarDao(){ return new CarDaoImpl<>();
    }
}
