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
 * This Class is used for
 *
 */
class DetailConfirmationBottomSheetDialog : BottomSheetDialogFragment() {
    var OPTION_PROCEED_CLICKED = 1
    var OPTION_DISMISS_CLICKED = 2
    lateinit var bottomSheetViewModel: BottomSheetViewModel
    var bottomSheetViewBinding: BottonSheetDialogScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        bottomSheetViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.botton_sheet_dialog_screen, container, false)
        bottomSheetViewBinding!!.viewModel = bottomSheetViewModel

        return bottomSheetViewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListenerEvent()
    }
    fun clickListenerEvent() {
        bottomSheetViewModel.userEventObserver.observe(this, Observer {
            when (it) {
                OPTION_PROCEED_CLICKED -> {
                    val model = arguments?.getParcelable<Hits>("hitModel")
                    val action =
                        DetailConfirmationBottomSheetDialogDirections.detailAction(
                            uri = "",
                            hitsModel = model!!
                        )
                    findNavController().navigate(action)
                }
            }
            dismiss()
        })
    }

}