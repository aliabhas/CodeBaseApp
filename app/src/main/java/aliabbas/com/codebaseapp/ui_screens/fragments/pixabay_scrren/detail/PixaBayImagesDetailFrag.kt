package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.detail

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.databinding.PixabayDetailScreenBinding
import aliabbas.com.codebaseapp.viewmodels.pixabay_detail.PixaBayDetailViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created By Ali Abbas
 */
public class PixaBayImagesDetailFrag @Inject constructor() : DaggerFragment() {
    var pixaBayImagesScreenBinding: PixabayDetailScreenBinding? = null
    val args: PixaBayImagesDetailFragArgs by navArgs()
    var pixaDetailViewModel: PixaBayDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.setTitle(R.string.detail_screen)
        pixaBayImagesScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.pixabay_detail_screen, container, false)
        pixaDetailViewModel =
            ViewModelProviders.of(this).get(
                PixaBayDetailViewModel::class.java
            )
        pixaDetailViewModel!!.setUpDataForAccount(args.hitsModel)
        pixaDetailViewModel!!.getHitsModel().observe(this, Observer {
            System.out.println("")
            pixaBayImagesScreenBinding!!.viewModel = pixaDetailViewModel
        })
        //pixaBayImagesScreenBinding!!.viewModel = pixaDetailViewModel
        pixaBayImagesScreenBinding!!.lifecycleOwner = this
        return pixaBayImagesScreenBinding!!.root
    }

}
