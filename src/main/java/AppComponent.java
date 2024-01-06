import dagger.Component;
import pl.dc4b.cardirectory.dao.CalcDao;

@Component(modules = AppModule.class)
public interface AppComponent {
    CalcDao calcDao(); // Dagger will provide the implementation
}
