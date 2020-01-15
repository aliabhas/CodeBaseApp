package aliabbas.com.codebaseapp.viewmodels.pixabay_detail

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

/**
 * Created By Ali Abbas on on 14,January,2020
 * This Class is used for
 *
 */
class PixaBayDetailViewModel : ViewModel() {
    /**
     * This object contains all the details related to image category and
     * what we have to display in Detail Screen
     * This object is being observed in Fragment and is bounded to View with Databinding,
     * any change will be reflected in View with the help of LiveData
     */
    val hitsObject: MutableLiveData<Hits> by lazy {
        MutableLiveData<Hits>()
    }

    /**
     * Through this function we are setting the Object to display in detail screen
     * that function is being called from Fragment
     *
     */
    fun setUpDataForAccount(accountObject: Hits) {
        hitsObject.value = accountObject
    }

    /**
     * This function is used to start observing this object in fragment so,
     * we get notified if there is any change in Object.
     */
    fun getHitsModel(): LiveData<Hits> {
        return hitsObject
    }

    companion object {
        /**
         * This function is used to display the image of Image category
         * that we are displaying in Detail Screen with the help of Databinding
         */
        @BindingAdapter("largeImageURL")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide
                .with(view.context)
                .load(imageUrl)
                .error(R.drawable.placeholder)
                .into(view)

        }
    }
}