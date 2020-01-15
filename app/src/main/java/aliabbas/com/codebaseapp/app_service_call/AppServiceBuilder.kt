package aliabbas.com.codebaseapp.app_service_call

import aliabbas.com.codebaseapp.utils.Constants
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppServiceBuilder @Inject constructor(var api: Api) {

    fun getSearchedData(
        singleObserver: SingleObserver<Any>,
        searchedUrl: String
    ) {

        var urlToHit = Constants.BASE_URL + searchedUrl
        api.gettingSalesDataFromGet(urlToHit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObserver)
    }

}