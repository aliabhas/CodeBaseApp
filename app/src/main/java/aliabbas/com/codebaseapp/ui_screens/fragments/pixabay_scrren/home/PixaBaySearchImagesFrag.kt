package aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.home

import aliabbas.com.codebaseapp.R
import aliabbas.com.codebaseapp.databinding.PixaBayImagesScreenBinding
import aliabbas.com.codebaseapp.di_dependencies.app_scopes.ActivityScoped
import aliabbas.com.codebaseapp.ui_screens.fragments.pixabay_scrren.adapter.PixaBaySearchAdapter
import aliabbas.com.codebaseapp.viewmodels.pixabay_home.PixaBayImagesViewModel
import aliabbas.com.codebaseapp.viewmodels.pixabay_home.ViewModelFactory
import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScoped
class PixaBaySearchImagesFrag @Inject constructor() : DaggerFragment(),
    SearchView.OnQueryTextListener {
    var pixaBayImagesScreenBinding: PixaBayImagesScreenBinding? = null
    private var searchView: SearchView? = null
    @Inject
    @JvmField
    var viewModelFactory: ViewModelFactory? = null
    @Inject
    @JvmField
    var pixaBaySearchAdapter: PixaBaySearchAdapter? = null
    private lateinit var pixaBayViewModel: PixaBayImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pixaBayViewModel =
            ViewModelProviders.of(this, viewModelFactory).get<PixaBayImagesViewModel>(
                PixaBayImagesViewModel::class.java
            )
        System.out.println("")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pixaBayImagesScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.pixa_bay_images_screen, container, false)

        pixaBayImagesScreenBinding!!.viewModel = pixaBayViewModel
        pixaBayImagesScreenBinding!!.lifecycleOwner = this

        return pixaBayImagesScreenBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.setTitle(R.string.home_screen)
        pixaBayViewModel.getPixaBayData().observe(viewLifecycleOwner,
            Observer<ImagesSearchedSealed> { data ->
                when (data) {
                    is ImagesSearchedSealed.ImagesListDetailsState -> {
                        pixaBaySearchAdapter?.setValues(data.lstHits, this@PixaBaySearchImagesFrag)
                        pixaBayViewModel.setProgressVisibilty(true)
                        if(data.lstHits.isNotEmpty()) {
                            pixaBayViewModel.setRequestFailureStatus(false)
                        }else
                            pixaBayViewModel.setRequestFailureStatus(true)
                    }
                    is ImagesSearchedSealed.ApiFailureNoDataState -> {
                        pixaBayViewModel.setProgressVisibilty(true)
                        pixaBayViewModel.setRequestFailureStatus(true)
                    }
                }


            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

    }

    private fun initializeView() {
        setHasOptionsMenu(true)
        pixaBayImagesScreenBinding?.recyclerViewImagesSearch?.run {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = pixaBaySearchAdapter
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        initializeSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Initialize search view
     *
     * @param menu SearchView options menu
     */
    private fun initializeSearchView(menu: Menu) {
        searchView = menu.findItem(R.id.menu_product_search).actionView as SearchView
        searchView?.run {
            //Set the Query Change Listener
            setOnQueryTextListener(this@PixaBaySearchImagesFrag)
            //Set tche hint for the search query
            queryHint = getString(R.string.search_hint)
            //This is only has a numeric input type as customer code
            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //pixaBayViewModel.getPixaBayData().value = null
        pixaBayViewModel.setProgressVisibilty(false)
        pixaBayViewModel.getPixaBayImagesData(query!!, pixaBayViewModel.getSearchedData())
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        /*
        * Had to implement this function, According to your requirement.
        */
        return false

    }
}
