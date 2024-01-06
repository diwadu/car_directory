import dagger.Module;
import dagger.Provides;
import pl.dc4b.cardirectory.dao.CalcDao;
import pl.dc4b.cardirectory.dao.CalcDaoImpl;

@Module
public class AppModule {
    @Provides
    static CalcDao provideMyService() {
        return new CalcDaoImpl();
    }
}
