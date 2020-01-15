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
        //Setting up the specific ViewModel to this Fragment
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
        //Binding the view to the specific DataBinding Object
        pixaBayImagesScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.pixa_bay_images_screen, container, false)

        //Binding the view to the specific ViewModel
        pixaBayImagesScreenBinding!!.viewModel = pixaBayViewModel

        //Binding the view to the lifecycle
        pixaBayImagesScreenBinding!!.lifecycleOwner = this

        return pixaBayImagesScreenBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.setTitle(R.string.home_screen)
        //Start observing the the DataSource for Category User Searched
        //For more info see function declaration
        pixaBayViewModel.getPixaBayData().observe(viewLifecycleOwner,
            Observer<ImagesSearchedSealed> { data ->
                when (data) {
                    //Means Received Data with any error
                    is ImagesSearchedSealed.ImagesListDetailsState -> {
                        //Setting up the list in Adapter
                        pixaBaySearchAdapter?.setValues(data.lstHits, this@PixaBaySearchImagesFrag)
                        //Making view visible and invisible
                        //see relevent function declaration for more detail
                        pixaBayViewModel.setProgressVisibilty(true)
                        if (data.lstHits.isNotEmpty()) {
                            pixaBayViewModel.setRequestFailureStatus(false)
                        } else
                            pixaBayViewModel.setRequestFailureStatus(true)
                    }
                    //Means Error occur while receiving the data
                    is ImagesSearchedSealed.ApiFailureNoDataState -> {
                        //Making view visible and invisible
                        //see relevent function declaration for more detail
                        pixaBayViewModel.setProgressVisibilty(true)
                        pixaBayViewModel.setRequestFailureStatus(true)
                    }
                }


            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Initializing view
        initializeView()

    }

    /**
     * Initializing the views
     * like setting up the orientaion and adapter for recyclerView
     */
    private fun initializeView() {
        setHasOptionsMenu(true)
        pixaBayImagesScreenBinding?.recyclerViewImagesSearch?.run {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = pixaBaySearchAdapter
            setHasFixedSize(true)
        }
    }

    /**
     * This is for setting up the option menu for search
     * from there we can search for different category of Images
     */
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

    /**
     * This function will get triggered when we submit the text,
     * what we have written in seatchView
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        //Making progressBar visible, for notifying user, that request is under process
        pixaBayViewModel.setProgressVisibilty(false)
        //Calling the data source function from where we'll get all the details
        //related to Category trying to search
        //for more info see function declaration
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
