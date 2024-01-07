import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import pl.dc4b.cardirectory.dao.CarDao;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideCarDaoFactory implements Factory<CarDao> {
  @Override
  public CarDao get() {
    return provideCarDao();
  }

  public static AppModule_ProvideCarDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CarDao provideCarDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.provideCarDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideCarDaoFactory INSTANCE = new AppModule_ProvideCarDaoFactory();
  }
}
