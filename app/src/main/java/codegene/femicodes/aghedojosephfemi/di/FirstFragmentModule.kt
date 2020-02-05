package codegene.femicodes.aghedojosephfemi.di

import codegene.femicodes.aghedojosephfemi.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FirstFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun firstFragment(): FirstFragment

}