package aliabbas.com.codebaseapp.di_dependencies.app_dependencies

import aliabbas.com.codebaseapp.activity.HomeActivity
import aliabbas.com.codebaseapp.di_dependencies.app_scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 */
@Module
abstract class ActivityBindingModule {
    //This will allow the HomeActivity to inject the dependencies
    //
    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeActivityRefMod::class,
        FragmentBindingModule::class]) //Used to inject the activity object.
    abstract fun mainActivityDependency(): HomeActivity
}