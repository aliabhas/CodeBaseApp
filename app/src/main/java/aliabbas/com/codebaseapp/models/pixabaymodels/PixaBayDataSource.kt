package aliabbas.com.codebaseapp.models.pixabaymodels

import aliabbas.com.codebaseapp.app_service_call.AppServiceBuilder
import aliabbas.com.codebaseapp.database.AppDatabase
import aliabbas.com.codebaseapp.database.entities.SearchedCache
import aliabbas.com.codebaseapp.database.entities.SearchedDetails
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home.ImagesSearchedSealed
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home.SearchedDbDataSource
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 11,January,2020
 * This Class is used for
 *
 */
@Suppress("UNCHECKED_CAST")
class PixaBayDataSource @Inject constructor(
    private val context: Activity, private val searchedCacheRef: SearchedDbDataSource
) {
    val DATA_FROM_SERVICE = 1
    val DATA_FROM_DATABASE = 2

    fun getCacheData(): LiveData<List<String>> {
        return AppDatabase.getAppDatabase(context)
            .getSearchedDetailsDao()
            .getCacheData()
    }

    fun getSearchedResult(
        appServiceBuilder: AppServiceBuilder,
        searchedQuery: String,
        listOfSearchedResult: LiveData<List<String>>
    ): MutableLiveData<ImagesSearchedSealed> {
        val mutableSearchedData:
                MutableLiveData<ImagesSearchedSealed> = MutableLiveData()
        if (searchedQuery.contentEquals("") ||
            (listOfSearchedResult.value != null
                    && listOfSearchedResult.value!!.indexOf(searchedQuery) >= 0)
        ) {
            searchedCacheRef
                .getSearchDataFromDb(
                    rxObserverForDataSource(
                        mutableSearchedData,
                        DATA_FROM_DATABASE,
                        searchedQuery
                    ), searchedQuery
                )
            return mutableSearchedData
        }
        appServiceBuilder.getSearchedData(
            rxObserverForDataSource(
                mutableSearchedData,
                DATA_FROM_SERVICE,
                searchedQuery
            ), searchedQuery
        )

        return mutableSearchedData
    }

    private fun rxObserverForDataSource(
        mutableSearchedData:
        MutableLiveData<ImagesSearchedSealed>, requestType: Int, searchedQuery: String
    ): SingleObserver<Any> {
        return object :
            SingleObserver<Any> {
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(any: Any) {
                when (requestType) {
                    DATA_FROM_SERVICE -> {
                        val searchedDetails = any as SearchedDetails
                        mutableSearchedData.value =
                            ImagesSearchedSealed.ImagesListDetailsState(searchedDetails.hits!!)
                        insertDataInSearchedCache(searchedQuery, searchedDetails.hits!!)
                    }
                    DATA_FROM_DATABASE -> {
                        val searchedDetails  = any as List<*>
                        mutableSearchedData.value =
                            ImagesSearchedSealed.ImagesListDetailsState(searchedDetails as List<Hits>)
                    }
                }
            }

            override fun onError(e: Throwable) {
                mutableSearchedData.value =
                    ImagesSearchedSealed.ApiFailureNoDataState("Not Data Found")
            }
        }
    }

    private fun insertDataInSearchedCache(
        searchedQuery: String,
        lstHints: List<Hits>
    ) {
        CoroutineScope(IO).launch {
            val searchedCache = SearchedCache()
            searchedCache.searchedString = searchedQuery
            AppDatabase.getAppDatabase(context).getCacheSearchedDataDao()
                .insertCacheData(searchedCache)
            AppDatabase.getAppDatabase(context).getCacheSearchedDataDao()
                .insertCacheResult(lstHints)
        }
    }

}