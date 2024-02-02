package pl.dc4b.cardirectory.config;

import dagger.Component;
import pl.dc4b.cardirectory.dao.CarDao;
import pl.dc4b.cardirectory.fxui.CarListController;
import pl.dc4b.cardirectory.fxui.LayoutController;
import pl.dc4b.cardirectory.services.CarService;
import pl.dc4b.cardirectory.textui.CarTextUIManager;

@Component(modules = AppModule.class)
public interface AppComponent {
    CarDao carDao();
    CarService carService();
    CarTextUIManager carTextUIManager();

    void inject(CarListController carListController);

    void inject(LayoutController layoutController);
}
