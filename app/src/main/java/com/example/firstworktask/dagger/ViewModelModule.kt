package com.example.firstworktask.dagger

import androidx.lifecycle.ViewModel
import com.example.firstworktask.main.FixturesViewModel
import com.example.firstworktask.second.FixtureDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {



    /*
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

     */


    @Binds
    @IntoMap
    @ViewModelKey(FixturesViewModel::class)
    abstract fun splashViewModel(viewModel: FixturesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FixtureDetailsViewModel::class)
    abstract fun splashSecondViewModel(viewModel: FixtureDetailsViewModel): ViewModel



}