package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home

import aliabbas.com.codebaseapp.models.pixabaymodels.Hits

/**
 * Created By Ali Abbas on on 15,January,2020
 * This Class is used for
 *
 */
sealed class ImagesSearchedSealed {
    data class ImagesListDetailsState(val lstHits: List<Hits>) : ImagesSearchedSealed()
    data class ApiFailureNoDataState(val response: String) : ImagesSearchedSealed()
}