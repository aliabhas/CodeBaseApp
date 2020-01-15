package aliabbas.com.codebaseapp.ui_screens.dialogs.bottom_sheet_dialog.viewbinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Ali Abbas on on 15,January,2020
 * This Class is used for
 *
 */
class BottomSheetViewModel : ViewModel() {

    val userEventObserver: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun userEventRegardingOption(optionType: Int) {
        userEventObserver.value = optionType
    }
}