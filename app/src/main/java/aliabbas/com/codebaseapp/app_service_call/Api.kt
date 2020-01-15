package aliabbas.com.codebaseapp.app_service_call

import aliabbas.com.codebaseapp.database.entities.SearchedDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created By Ali Abbas For Network Calls
 */
interface Api {

    @GET
    fun gettingSalesDataFromGet(@Url url: String): Single<SearchedDetails>

}