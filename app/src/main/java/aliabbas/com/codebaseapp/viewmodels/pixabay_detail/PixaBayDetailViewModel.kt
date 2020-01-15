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
    val hitsObject: MutableLiveData<Hits> by lazy {
        MutableLiveData<Hits>()
    }


    fun setUpDataForAccount(accountObject: Hits) {
        hitsObject.value = accountObject
    }

    fun getHitsModel(): LiveData<Hits> {
        return hitsObject
    }

    companion object {
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