package codegene.femicodes.aghedojosephfemi.ui.addFilter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import codegene.femicodes.aghedojosephfemi.repository.FilterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFilterViewModel @Inject constructor(private val repository: FilterRepository) :
    ViewModel() {


    fun addFilter(filter: FilterEntity) {
        viewModelScope.launch {
            repository.addFilter(filter)
        }
    }

}