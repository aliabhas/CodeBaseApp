package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.adapter

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.databinding.ItemPixalbayAccountsBinding
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import android.app.Activity
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
 */
class PixaBaySearchAdapter @Inject constructor(var activityReference: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var lstHits: List<Hits>? = mutableListOf()
    var adpterScoperFragment: Fragment? = null
    public fun setValues(lstHits: List<Hits>, fragment: Fragment) {
        if (this.lstHits == lstHits) {
            return
        }
        this.lstHits = lstHits
        notifyDataSetChanged()
        adpterScoperFragment = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemUnselectBinding = DataBindingUtil.inflate<ItemPixalbayAccountsBinding>(
            LayoutInflater.from(parent.context), R.layout.item_pixalbay_accounts,
            parent, false
        )
        return ConfigureViewHolderForUnselected(itemUnselectBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as
                ConfigureViewHolderForUnselected?
        holder!!.bindProfileDetails(lstHits!![position])
    }

    inner class ConfigureViewHolderForUnselected(var mItemPeopleBinding: ItemPixalbayAccountsBinding) :
        RecyclerView.ViewHolder(mItemPeopleBinding.mtrlCardView) {
        fun bindProfileDetails(hitsModel: Hits?) {
            mItemPeopleBinding.hitsModel = hitsModel
            mItemPeopleBinding.imageViewDetails.also {
                it.transitionName = hitsModel?.userImageURL

            }
            mItemPeopleBinding.mtrlCardView.setOnClickListener {

                //it.findNavController().navigate(R.id.my_dialog)

                var bundle = bundleOf("hitModel" to hitsModel!!)

                /* val action = PixaBayImagesFragDirections
                     .papu(hitsModel = hitsModel!!)
                 val extras = FragmentNavigatorExtras(
                     mItemPeopleBinding.imageViewDetails to hitsModel!!.userImageURL!!
                 )*/
                it.findNavController().navigate(R.id.papu, bundle)

                /*(adpterScoperFragment as PixaBayImagesFrag)
                    .navigateToDetailFragment(mItemPeopleBinding.imageViewDetails, hitsModel!!)*/
            }

        }
    }

    /*
    * val action = PixaBayImagesFragDirections
                    .detailAction(uri = hitsModel!!.userImageURL, hitsModel = hitsModel)
                val extras = FragmentNavigatorExtras(
                    mItemPeopleBinding.imageViewDetails to hitsModel.userImageURL!!
                )
                it.findNavController().navigate(action, extras)
    * */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount(): Int {
        return lstHits!!.size
    }

}