package aliabbas.com.codebaseapp.di_dependencies.app_dependencies

import aliabbas.com.codebaseapp.activity.HomeActivity
import android.app.Activity
import dagger.Binds
import dagger.Module

/*
 * Created by Ali Abbas
 * This module is used to inject activity references
 *
 */
@Module
abstract class HomeActivityRefMod {

    //With the help of this We can Inject the activity in Dependent classes
    //Making activity reference available for injection.
    @Binds
    abstract fun homeActivityRefrence(homeActivity: HomeActivity): Activity
}