package pl.dc4b.cardirectory.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.services.CarService;

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
public final class AppModule_ProvideCarServiceFactory implements Factory<CarService> {
  private final Provider<CarDao> carDaoProvider;

  public AppModule_ProvideCarServiceFactory(Provider<CarDao> carDaoProvider) {
    this.carDaoProvider = carDaoProvider;
  }

  @Override
  public CarService get() {
    return provideCarService(carDaoProvider.get());
  }

  public static AppModule_ProvideCarServiceFactory create(Provider<CarDao> carDaoProvider) {
    return new AppModule_ProvideCarServiceFactory(carDaoProvider);
  }

  public static CarService provideCarService(CarDao carDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.provideCarService(carDao));
  }
}
