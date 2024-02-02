package pl.dc4b.cardirectory.textui;

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
public final class CarTextUIManagerImpl_Factory implements Factory<CarTextUIManagerImpl> {
  private final Provider<CarDao> carDaoProvider;

  public CarTextUIManagerImpl_Factory(Provider<CarDao> carDaoProvider) {
    this.carDaoProvider = carDaoProvider;
  }

  @Override
  public CarTextUIManagerImpl get() {
    return newInstance(carDaoProvider.get());
  }

  public static CarTextUIManagerImpl_Factory create(Provider<CarDao> carDaoProvider) {
    return new CarTextUIManagerImpl_Factory(carDaoProvider);
  }

  public static CarTextUIManagerImpl newInstance(CarDao carDao) {
    return new CarTextUIManagerImpl(carDao);
  }
}
