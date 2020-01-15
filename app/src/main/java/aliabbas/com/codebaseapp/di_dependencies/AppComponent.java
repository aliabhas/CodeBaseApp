package aliabbas.com.codebaseapp.di_dependencies;

import android.app.Application;

import javax.inject.Singleton;

import aliabbas.com.codebaseapp.AndroidApplication;
import aliabbas.com.codebaseapp.di_dependencies.app_dependencies.ActivityBindingModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

;

/**
 * This is a Dagger component. Refer to {@link AndroidApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * AndroidApplication}.
 * <p>
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules =
        {AndroidSupportInjectionModule.class,
                ActivityBindingModule.class,
        })
public interface AppComponent extends AndroidInjector<AndroidApplication> {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}