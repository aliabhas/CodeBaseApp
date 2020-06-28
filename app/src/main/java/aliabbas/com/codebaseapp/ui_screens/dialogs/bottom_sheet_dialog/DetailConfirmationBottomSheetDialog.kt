package aliabbas.com.codebaseapp.ui_screens.dialogs.bottom_sheet_dialog

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.databinding.BottonSheetDialogScreenBinding
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import aliabbas.com.codebaseapp.ui_screens.dialogs.bottom_sheet_dialog.viewbinding.BottomSheetViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * Created By Ali Abbas on on 15,January,2020
 * This Class is BottomSheet Dialog
 *
 */
class DetailConfirmationBottomSheetDialog : BottomSheetDialogFragment() {
    //Allowing app to move to the next detail screen
    private var OPTION_PROCEED_CLICKED = 1
    //ViewModel related to Dialog
    lateinit var bottomSheetViewModel: BottomSheetViewModel
    //Binding related to the particular view
    var bottomSheetViewBinding: BottonSheetDialogScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setting up the viewmodel for the particular Dialog
        bottomSheetViewModel =
            ViewModelProviders.of(this).get(
                BottomSheetViewModel::class.java
            )
    }

    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        //Binding dataBinding object to the specific view that we are inflating in the dialog
        bottomSheetViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.botton_sheet_dialog_screen, container, false)
        //Setting up the viewModel of dialog
        bottomSheetViewBinding!!.viewModel = bottomSheetViewModel

        //Returning the view
        return bottomSheetViewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListenerEvent()
    }

    /**
     * Implementing the clicklistener
     * We are getting the callback with the help of Databinding
     * onclick callback, that we have implemented in ViewModel from there with the help of
     * LiveData onchange event calls and in obserever we have define our logic
     * Means,
     * if user user has pressed Yes -> Navigate to detail screen
     * if user user has pressed No -> Simply Dismiss the dialog
     *
     */
    private fun clickListenerEvent() {
        bottomSheetViewModel.userEventObserver.observe(this, Observer {
            when (it) {
                //User pressed yes SO NAVIGATE to detail screen
                //Yes option selected
                //See the declaration for more detail
                OPTION_PROCEED_CLICKED -> {
                    //Getting the data from bundle
                    val model = arguments?.getParcelable<Hits>("hitModel")
                    //Setting up the bundles for the next screen
                    val action =
                        DetailConfirmationBottomSheetDialogDirections.detailAction(
                            uri = "",
                            hitsModel = model!!
                        )
                    //Navigating to the detail screen
                    findNavController().navigate(action)
                }
            }
            dismiss()
        })
    }

}