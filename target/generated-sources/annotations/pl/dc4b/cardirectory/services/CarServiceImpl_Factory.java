package pl.dc4b.cardirectory.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
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
public final class CarServiceImpl_Factory implements Factory<CarServiceImpl> {
  private final Provider<CarDao> carDaoProvider;

  public CarServiceImpl_Factory(Provider<CarDao> carDaoProvider) {
    this.carDaoProvider = carDaoProvider;
  }

  @Override
  public CarServiceImpl get() {
    return newInstance(carDaoProvider.get());
  }

  public static CarServiceImpl_Factory create(Provider<CarDao> carDaoProvider) {
    return new CarServiceImpl_Factory(carDaoProvider);
  }

  public static CarServiceImpl newInstance(CarDao carDao) {
    return new CarServiceImpl(carDao);
  }
}
