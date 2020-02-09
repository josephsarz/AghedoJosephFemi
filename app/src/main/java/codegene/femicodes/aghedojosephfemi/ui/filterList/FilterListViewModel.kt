package codegene.femicodes.aghedojosephfemi.ui.filterList

import androidx.lifecycle.ViewModel
import codegene.femicodes.aghedojosephfemi.repository.FilterRepository
import javax.inject.Inject

class FilterListViewModel @Inject constructor(private val repository: FilterRepository) :
    ViewModel() {

    val legoSets by lazy {
        repository.observeFilter()
    }

}