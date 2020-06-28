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
    /**
     * default category that we have to search
     */
    private val defaultSearchString: String = "fruit"
    /**
     * List for getting all the data related to search user has did
     * For Example,
     * This list will hold all the data related to search = "Flower"
     * All of the imageurl's , text details etc
     */
    private var lstPixaImages: MediatorLiveData<ImagesSearchedSealed> = MediatorLiveData()
    /**
     * List for getting all the data that user have searched,
     * For Example:
     * This will hold searched queries like "Flower,Fruits"
     * Till the application lifecycle as this is InMemory cache database,
     * this will only last till application is on active state
     *
     */
    private var lstOfSearchedResult: LiveData<List<String>> = MutableLiveData()
    /**
     * This variable will be used for the state of ProgressBar
     * Visible , gone
     * During Api call this will be visible, on response this will be invisible
     * and data will be visible
     */
    var isDataAvailable: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * This variable will be used for the state of TextView
     * Visible , gone
     * This will be VISIBLE only in case of, we did not receive any data from server
     * other than that this will be INVISIBLE
     */
    var isRequestFailure: MutableLiveData<Boolean> = MutableLiveData()

    var searchedQueryString: MutableLiveData<String> = MutableLiveData()


    init {
        /**
         * Keeping tract of all the searched data using live data with Room
         * like 'fruits' , 'flower' etc all the search history
         */
        lstOfSearchedResult = pixaBayDataSource.getCacheData()
        searchedQueryString.value = defaultSearchString
        //see function declaration for more detail
        getPixaBayImagesData(defaultSearchString, lstOfSearchedResult)
    }


    fun getSearchedData(): LiveData<List<String>> {
        return lstOfSearchedResult
    }

    fun getPixaBayData(): LiveData<ImagesSearchedSealed> {
        return lstPixaImages
    }

    fun getSearchedQueryString(): LiveData<String> {
        return searchedQueryString
    }

    /**
     * This function is the data source provider for the image category searched
     * this will initiate the process of calling an api for that category or
     * wether we have to look into our cache to get the details for the searched Category
     *
     * Then return data will be stored in MutableLiveData and against that we have
     * an observer it will be triggered when there is a change in the data
     */
    fun getPixaBayImagesData(
        searchedText: String,
        listOfSearchedResults: LiveData<List<String>>
    ) {
        lstPixaImages.addSource(
            pixaBayDataSource.getSearchedResult(
                appServiceBuilder,
                searchedText, listOfSearchedResults
            )
        ) {
            lstPixaImages.value = it
            searchedQueryString.value = searchedText

        }
    }

    /**
     * This variable is bounded to View by the help of Databinding
     * So, the change in values will be reflected automatically in view
     * This function will be used for the state of ProgressBar
     * Visible , gone
     * During Api call this will be visible, on response this will be invisible
     * and data will be visible
     */
    fun setProgressVisibility(viewVisibility: Boolean) {
        isDataAvailable.value = viewVisibility
    }

    /**
     * This variable is bounded to View by the help of Databinding
     * So, the change in values will be reflected automatically in view
     * This function will be used for the state of TextView
     * Visible , gone
     * This will be VISIBLE only in case of, we did not receive any data from server
     * other than that this will be INVISIBLE
     */
    fun setRequestFailureStatus(viewVisibility: Boolean) {
        isRequestFailure.value = viewVisibility
    }

    companion object {
        /**
         * This function is used to display the image of Image category
         * that we are displaying in RecyclerView with the help of Databinding
         */
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
