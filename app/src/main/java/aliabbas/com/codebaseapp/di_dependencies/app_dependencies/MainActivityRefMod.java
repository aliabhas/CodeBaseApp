package aliabbas.com.codebaseapp.di_dependencies.app_dependencies;

import android.app.Activity;

import aliabbas.com.codebaseapp.activity.MainActivity;
import dagger.Binds;
import dagger.Module;

/*
 * Created by Ali Abbas
 * This module is used to tell the DI about the Network helpers we have and the object we need to inject.
 *
 */
@Module
public abstract class MainActivityRefMod {

    @Binds
    abstract Activity homeActivityRefrence(MainActivity homeActivity);


}