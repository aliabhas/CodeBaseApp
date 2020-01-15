package aliabbas.com.codebaseapp;

import android.app.Application;

import aliabbas.com.codebaseapp.database.entities.SearchedDetails;
import aliabbas.com.codebaseapp.di_dependencies.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * We create a custom {@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
public final class AndroidApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}