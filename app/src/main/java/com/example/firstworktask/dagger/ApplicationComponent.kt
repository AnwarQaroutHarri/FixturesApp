package com.example.firstworktask.dagger

import android.app.Application
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.main.FixturesViewModel
import com.example.firstworktask.second.FixtureDetailsViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitInstance::class, ViewModelModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<MyApplication> {


    fun getMainViewModel(): FixturesViewModel
    fun getSecondViewModel(): FixtureDetailsViewModel

    //fun inject(activity: MainActivity)

    //fun myViewModel() : InjectableViewModelFactory<MainActivityViewModel>



    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }







    // fun getRetrofitInstance() : RetrofitInstanceModule

}