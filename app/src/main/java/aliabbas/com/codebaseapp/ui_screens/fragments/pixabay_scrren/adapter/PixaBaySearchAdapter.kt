package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.adapter

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.databinding.ItemPixalbayAccountsBinding
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 * Created by Ali Abbas
 * Testing for the plugin updaye branch
 * Are pappu cant dance sala
 * This class hold the detail to display the recyclerview data
 * Hello Version 1.34
 * Hello test new branch
 * Doing changes in master, thanks
 */
class PixaBaySearchAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val DEFAULT_VIEW_TYPE = 0
    var lstHits: List<Hits>? = mutableListOf()
    var adpterScoperFragment: Fragment? = null
    /**
     * Setting the details to display in the Recyclerview
     */
    public fun setValues(lstHits: List<Hits>, fragment: Fragment) {
        if (this.lstHits == lstHits) {
            return
        }
        this.lstHits = lstHits
        notifyDataSetChanged()
        adpterScoperFragment = fragment
    }

    //inflating the layout and binding it to the particular Databinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemUnselectBinding = DataBindingUtil.inflate<ItemPixalbayAccountsBinding>(
            LayoutInflater.from(parent.context), R.layout.item_pixalbay_accounts,
            parent, false
        )
        //Returning Viewholder after the binded view
        return ConfigureViewHolderForUnselected(itemUnselectBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as
                ConfigureViewHolderForUnselected?
        holder!!.bindProfileDetails(lstHits!![position])
    }

    /**
     * Here setting up the detail to the views
     * And adding the ClickListener
     * view is binded with the Databinding so we just have to set the relevent object
     * and data will be populated.
     */
    inner class ConfigureViewHolderForUnselected(var mItemPeopleBinding: ItemPixalbayAccountsBinding) :
        RecyclerView.ViewHolder(mItemPeopleBinding.mtrlCardView) {
        fun bindProfileDetails(hitsModel: Hits?) {
            //Thats the binding object for the details to display on screen
            mItemPeopleBinding.hitsModel = hitsModel
            mItemPeopleBinding.mtrlCardView.setOnClickListener {
                //Displaying the bottom sheet while passing the bundles with it
                //setting up the bundles
                var bundle = bundleOf("hitModel" to hitsModel!!)
                //with the help of Navigation Component navigating Displaying Dialog
                it.findNavController().navigate(R.id.navigateToBottomSheet, bundle)

            }

        }
    }

    /**
     * Returning the id for the view
     * Stable View
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Type of views we want to display in recyclerview
     * in our case we have only 1 view, so we are only returning only 1 type of view
     * if we have multiple view we could have returned different kind of views
     */
    override fun getItemViewType(position: Int): Int {
        return DEFAULT_VIEW_TYPE
    }

    /**
     * Returning the size of list
     */
    override fun getItemCount(): Int {
        return lstHits!!.size
    }

}