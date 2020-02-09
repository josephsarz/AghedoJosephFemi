package codegene.femicodes.aghedojosephfemi.ui.filterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import codegene.femicodes.aghedojosephfemi.R
import codegene.femicodes.aghedojosephfemi.domain.Result
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filter_list.*
import javax.inject.Inject

class FilterListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val filterViewModel: FilterListViewModel by viewModels { viewModelFactory }

    private val filterListAdapter = FilterListAdapter { filter ->
        findNavController().navigate(
            FilterListFragmentDirections.actionFilterListFragmentToFilteredResultFragment(
                filter
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filterListAdapter
        }

        fab.setOnClickListener {
            findNavController().navigate(FilterListFragmentDirections.actionFilterListFragmentToAddFilterFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        filterViewModel.legoSets.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    filterListAdapter.submitList(result?.data)
                    progress.visibility = View.GONE
                }
                Result.Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                }
                Result.Status.ERROR -> {
                    progress.visibility = View.GONE
                    Snackbar.make(parentLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

    }

}