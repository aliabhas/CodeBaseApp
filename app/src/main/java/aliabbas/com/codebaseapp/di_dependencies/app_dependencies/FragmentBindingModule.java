package aliabbas.com.codebaseapp.di_dependencies.app_dependencies;

import aliabbas.com.codebaseapp.di_dependencies.app_scopes.FragmentScoped;
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.detail.PixaBayImagesDetailFrag;
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home.PixaBaySearchImagesFrag;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 */
@Module
public abstract class FragmentBindingModule {
    //Use this annotation for any activity or fragment related to view.
    @FragmentScoped
    @ContributesAndroidInjector //Used to inject the fragment object.
    public abstract PixaBaySearchImagesFrag pixaBayImagesFragReference();

    //Use this annotation for any activity or fragment related to view.
    @FragmentScoped
    @ContributesAndroidInjector //Used to inject the fragment object.
    public abstract PixaBayImagesDetailFrag pixaBayImagesDetail();

}
