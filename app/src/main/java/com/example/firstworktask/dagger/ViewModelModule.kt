/*package com.example.firstworktask.dagger

import androidx.lifecycle.ViewModel
import com.example.firstworktask.main.FixturesViewModel
import com.example.firstworktask.second.FixtureDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class,FragmentComponent::class)
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(FixturesViewModel::class)
    abstract fun splashViewModel(viewModel: FixturesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FixtureDetailsViewModel::class)
    abstract fun splashSecondViewModel(viewModel: FixtureDetailsViewModel): ViewModel



}

 */