package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home

import aliabbas.com.codebaseapp.database.AppDatabase
import android.app.Activity
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 14,January,2020
 * This Class is used for
 *
 */
class SearchedDbDataSource @Inject constructor(var activity: Activity) {


    public fun getSearchDataFromDb(singleObserver: SingleObserver<Any>, searchedQuery: String) {
        val searchedLikeString ="%"+searchedQuery+"%"
        AppDatabase.getAppDatabase(activity)
            .getCacheSearchedDataDao()
            .getCacheData(searchedLikeString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObserver)

    }

}