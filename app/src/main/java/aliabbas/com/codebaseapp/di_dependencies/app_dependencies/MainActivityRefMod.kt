package aliabbas.com.codebaseapp.di_dependencies.app_dependencies

import aliabbas.com.codebaseapp.activity.MainActivity
import android.app.Activity
import dagger.Binds
import dagger.Module

/*
 * Created by Ali Abbas
 * This module is used to tell the DI about the Network helpers we have and the object we need to inject.
 *
 */
@Module
abstract class MainActivityRefMod {
    @Binds
    abstract fun homeActivityRefrence(homeActivity: MainActivity?): Activity?
}