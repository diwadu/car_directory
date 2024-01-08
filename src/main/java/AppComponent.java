import dagger.Component;
import pl.dc4b.cardirectory.dao.CalcDao;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.services.CarService;

@Component(modules = AppModule.class)
public interface AppComponent {
    CalcDao calcDao();
    CarDao carDao();
    CarService carService();
}
