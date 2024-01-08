package pl.dc4b.cardirectory.fxui;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
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
public final class CarListController_Factory implements Factory<CarListController> {
  private final Provider<CarService> carServiceProvider;

  public CarListController_Factory(Provider<CarService> carServiceProvider) {
    this.carServiceProvider = carServiceProvider;
  }

  @Override
  public CarListController get() {
    return newInstance(carServiceProvider.get());
  }

  public static CarListController_Factory create(Provider<CarService> carServiceProvider) {
    return new CarListController_Factory(carServiceProvider);
  }

  public static CarListController newInstance(CarService carService) {
    return new CarListController(carService);
  }
}
