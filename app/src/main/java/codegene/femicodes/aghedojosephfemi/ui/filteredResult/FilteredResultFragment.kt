package codegene.femicodes.aghedojosephfemi.ui.filteredResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import codegene.femicodes.aghedojosephfemi.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filtered_result.*
import javax.inject.Inject


class FilteredResultFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val filteredResultViewModel: FilteredResultViewModel by viewModels { viewModelFactory }

    private val args: FilteredResultFragmentArgs by navArgs()

    private val filteredResultAdapter = FilteredResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filtered_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filteredResultAdapter
        }

        filteredResultViewModel.filter = args.data
        filteredResultViewModel.filteredResult?.observe(viewLifecycleOwner, Observer { owners ->
            filteredResultAdapter.submitList(owners)
            progress.visibility = View.GONE
            error_group.visibility = if (owners.isEmpty()) View.VISIBLE else View.GONE
        })

    }
}