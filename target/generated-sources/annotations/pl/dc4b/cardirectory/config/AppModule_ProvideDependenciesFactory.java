package pl.dc4b.cardirectory.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.textui.CarTextUIManager;

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
public final class AppModule_ProvideDependenciesFactory implements Factory<CarTextUIManager> {
  private final Provider<CarDao> carDaoProvider;

  public AppModule_ProvideDependenciesFactory(Provider<CarDao> carDaoProvider) {
    this.carDaoProvider = carDaoProvider;
  }

  @Override
  public CarTextUIManager get() {
    return provideDependencies(carDaoProvider.get());
  }

  public static AppModule_ProvideDependenciesFactory create(Provider<CarDao> carDaoProvider) {
    return new AppModule_ProvideDependenciesFactory(carDaoProvider);
  }

  public static CarTextUIManager provideDependencies(CarDao carDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.provideDependencies(carDao));
  }
}
