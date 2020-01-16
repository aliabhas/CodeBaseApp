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
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created By Ali Abbas
 * This fragment is used to display the details of Image that we selected
 * in screen previous
 */
public class PixaBayImagesDetailFrag @Inject constructor() : DaggerFragment() {
    //Databinding object
    var pixaBayImagesScreenBinding: PixabayDetailScreenBinding? = null
    //Argument set for the current Fragment, that has set by the previous screens
    val args: PixaBayImagesDetailFragArgs by navArgs()
    //ViewModel for the specific screen
    var pixaDetailViewModel: PixaBayDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Setting up the screen title
        activity!!.setTitle(R.string.detail_screen)
        //Binding the view to relevant DataBinding Object
        pixaBayImagesScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.pixabay_detail_screen, container, false)
        //Setting up the specific ViewModel
        pixaDetailViewModel =
            ViewModelProviders.of(this).get(
                PixaBayDetailViewModel::class.java
            )
        //Setting up the Image details with the ViewModel
        pixaDetailViewModel!!.setUpDataForAccount(args.hitsModel)
        //Start observing the the Image Detail Object
        //For more info see function decleration
        pixaDetailViewModel!!.getHitsModel().observe(this, Observer {
            System.out.println("")
            pixaBayImagesScreenBinding!!.viewModel = pixaDetailViewModel
        })
        //Binding the lifecycle with the view
        pixaBayImagesScreenBinding!!.lifecycleOwner = this
        return pixaBayImagesScreenBinding!!.root
    }

}
