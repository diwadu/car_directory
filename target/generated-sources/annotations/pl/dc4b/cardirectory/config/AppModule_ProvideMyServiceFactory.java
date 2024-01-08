package pl.dc4b.cardirectory.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import pl.dc4b.cardirectory.dao.CalcDao;

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
public final class AppModule_ProvideMyServiceFactory implements Factory<CalcDao> {
  @Override
  public CalcDao get() {
    return provideMyService();
  }

  public static AppModule_ProvideMyServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalcDao provideMyService() {
    return Preconditions.checkNotNullFromProvides(AppModule.provideMyService());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideMyServiceFactory INSTANCE = new AppModule_ProvideMyServiceFactory();
  }
}
