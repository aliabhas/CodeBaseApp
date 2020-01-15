package aliabbas.com.codebaseapp.recyclerview_decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Ali Abbas on on 13,January,2020
 * This Class is used for
 *
 */
class VerticalSpaceItemDecorationextends : RecyclerView.ItemDecoration() {
    var verticalSpaceHeight: Int = 30;


    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight!!;
    }
}