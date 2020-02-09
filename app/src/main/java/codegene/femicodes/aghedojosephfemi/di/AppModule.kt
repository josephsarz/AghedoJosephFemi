package codegene.femicodes.aghedojosephfemi.di

import androidx.lifecycle.ViewModel
import codegene.femicodes.aghedojosephfemi.ui.addFilter.AddFilterFragment
import codegene.femicodes.aghedojosephfemi.ui.addFilter.AddFilterViewModel
import codegene.femicodes.aghedojosephfemi.ui.filterList.FilterListFragment
import codegene.femicodes.aghedojosephfemi.ui.filterList.FilterListViewModel
import codegene.femicodes.aghedojosephfemi.ui.filteredResult.FilteredResultFragment
import codegene.femicodes.aghedojosephfemi.ui.filteredResult.FilteredResultViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun filterListFragment(): FilterListFragment

    @Binds
    @IntoMap
    @ViewModelKey(FilterListViewModel::class)
    abstract fun bindFilterListViewModel(FilterListViewModel: FilterListViewModel): ViewModel

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun filteredResultFragment(): FilteredResultFragment

    @Binds
    @IntoMap
    @ViewModelKey(FilteredResultViewModel::class)
    abstract fun bindFilteredResultViewModel(FilterListViewModel: FilteredResultViewModel): ViewModel

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun addFilterFragmentfilteredResultFragment(): AddFilterFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddFilterViewModel::class)
    abstract fun bindAddFilterViewModel(addFilterViewModel: AddFilterViewModel): ViewModel

}