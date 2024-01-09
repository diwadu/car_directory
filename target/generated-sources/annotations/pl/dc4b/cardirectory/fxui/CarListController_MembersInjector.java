package pl.dc4b.cardirectory.fxui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.dc4b.cardirectory.services.CarService;

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
public final class CarListController_MembersInjector implements MembersInjector<CarListController> {
  private final Provider<CarService> carServiceProvider;

  public CarListController_MembersInjector(Provider<CarService> carServiceProvider) {
    this.carServiceProvider = carServiceProvider;
  }

  public static MembersInjector<CarListController> create(Provider<CarService> carServiceProvider) {
    return new CarListController_MembersInjector(carServiceProvider);
  }

  @Override
  public void injectMembers(CarListController instance) {
    injectInjectDependencies(instance, carServiceProvider.get());
  }

  public static void injectInjectDependencies(CarListController instance, CarService carService) {
    instance.injectDependencies(carService);
  }
}
