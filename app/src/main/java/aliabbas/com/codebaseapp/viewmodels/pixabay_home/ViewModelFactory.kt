package aliabbas.com.codebaseapp.viewmodels.pixabay_home

import aliabbas.com.codebaseapp.app_service_call.AppServiceBuilder
import aliabbas.com.codebaseapp.models.pixabaymodels.PixaBayDataSource
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 *
 */
public class ViewModelFactory @Inject constructor(
    public var repository: AppServiceBuilder?,
    public var pixaBayDataSource: PixaBayDataSource?
) : ViewModelProvider.Factory {


    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PixaBayImagesViewModel::class.java)) {
            return PixaBayImagesViewModel(
                repository!!,
                pixaBayDataSource!!
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
