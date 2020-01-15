package aliabbas.com.codebaseapp.di_dependencies.app_dependencies

import aliabbas.com.codebaseapp.activity.MainActivity
import aliabbas.com.codebaseapp.di_dependencies.app_scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityRefMod::class, ApiServiceModule::class, FragmentBindingModule::class]) //Used to inject the activity object.
    abstract fun mainActivityDependency(): MainActivity?
}