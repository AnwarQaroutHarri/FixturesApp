package com.example.firstworktask.dagger

import androidx.lifecycle.ViewModel
import com.example.firstworktask.main.MainViewModel
import com.example.firstworktask.second.SecondViewModel
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
    @ViewModelKey(MainViewModel::class)
    abstract fun splashViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun splashSecondViewModel(viewModel: SecondViewModel): ViewModel



}