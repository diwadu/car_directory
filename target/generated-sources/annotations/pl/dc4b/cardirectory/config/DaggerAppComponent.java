package pl.dc4b.cardirectory.config;

import dagger.internal.DaggerGenerated;
import javax.annotation.processing.Generated;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.fxui.CarListController;
import pl.dc4b.cardirectory.fxui.CarListController_MembersInjector;
import pl.dc4b.cardirectory.fxui.LayoutController;
import pl.dc4b.cardirectory.services.CarService;
import pl.dc4b.cardirectory.textui.CarTextUIManager;

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
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    public AppComponent build() {
      return new AppComponentImpl();
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AppComponentImpl appComponentImpl = this;

    private AppComponentImpl() {


    }

    @Override
    public CarDao carDao() {
      return AppModule_ProvideCarDaoFactory.provideCarDao();
    }

    @Override
    public CarService carService() {
      return AppModule_ProvideCarServiceFactory.provideCarService(AppModule_ProvideCarDaoFactory.provideCarDao());
    }

    @Override
    public CarTextUIManager carTextUIManager() {
      return AppModule_ProvideDependenciesFactory.provideDependencies(AppModule_ProvideCarDaoFactory.provideCarDao());
    }

    @Override
    public void inject(CarListController carListController) {
      injectCarListController(carListController);
    }

    @Override
    public void inject(LayoutController layoutController) {
    }

    private CarListController injectCarListController(CarListController instance) {
      CarListController_MembersInjector.injectInjectDependencies(instance, carService());
      return instance;
    }
  }
}
