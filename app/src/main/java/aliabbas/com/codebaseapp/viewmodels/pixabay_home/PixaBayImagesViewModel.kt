package aliabbas.com.codebaseapp.viewmodels.pixabay_home

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.app_service_call.AppServiceBuilder
import aliabbas.com.codebaseapp.models.pixabaymodels.PixaBayDataSource
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home.ImagesSearchedSealed
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import javax.inject.Inject


/**
 * View Model to care of all the data for PixaBay fragment
 * 1- You will get the searched data from PixaBay Site
 * 2- you will receive all the searched data from InMemory Database cache, Using Live Data
 */
class PixaBayImagesViewModel @Inject constructor(
    var appServiceBuilder: AppServiceBuilder,
    var pixaBayDataSource: PixaBayDataSource
) : ViewModel() {
    val defaultSearchString: String = "fruit"
    /**
     * List for getting all the data related to search user has did
     * For Example,
     * This list will hold all the data related to search = "Flower"
     * All of the imageurl's , text details etc
     */
    var lstPixaImages: MediatorLiveData<ImagesSearchedSealed> = MediatorLiveData()
    /**
     * List for getting all the data that user have searched,
     * For Example:
     * This will hold searched queries like "Flower,Fruits"
     * Till the application lifecycle as this is InMemory cache database,
     * this will only last till application is on active state
     *
     */
    var lstOfSearcehReults: LiveData<List<String>> = MutableLiveData()
    var isDataAvailable: MutableLiveData<Boolean> = MutableLiveData()
    var isRequestFailure: MutableLiveData<Boolean> = MutableLiveData()


    init {
        lstOfSearcehReults = pixaBayDataSource.getCacheData()
        getPixaBayImagesData(defaultSearchString, lstOfSearcehReults)
    }


    fun getSearchedData(): LiveData<List<String>> {
        return lstOfSearcehReults
    }

    fun getPixaBayData(): LiveData<ImagesSearchedSealed> {
        return lstPixaImages
    }

    fun getPixaBayImagesData(
        searchedText: String,
        lstOfSearcehReults: LiveData<List<String>>
    ) {
        lstPixaImages.addSource(
            pixaBayDataSource.getSearchedResult(
                appServiceBuilder,
                searchedText, lstOfSearcehReults
            )
        ) {
            lstPixaImages.value = it
        }
    }

    fun setProgressVisibilty(viewVisibility: Boolean) {
        isDataAvailable.value = viewVisibility
    }

    fun setRequestFailureStatus(viewVisibility: Boolean) {
        isRequestFailure.value = viewVisibility
    }

    companion object {
        @BindingAdapter("thumbnail")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide
                .with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view)

        }
    }
}
