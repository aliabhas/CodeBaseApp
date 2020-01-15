package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home

import aliabbas.com.codebaseapp.models.pixabaymodels.Hits

/**
 * Created By Ali Abbas on on 15,January,2020
 * This Class is used for setting up the success response of failure response
 * based on result we received from the server
 * and work accordingly
 *
 */
sealed class ImagesSearchedSealed {
    //Means Received Data with any error
    data class ImagesListDetailsState(val lstHits: List<Hits>) : ImagesSearchedSealed()

    //Means Error occur while receiving the data
    data class ApiFailureNoDataState(val response: String) : ImagesSearchedSealed()
}