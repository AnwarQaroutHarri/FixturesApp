package com.example.firstworktask.dagger

import android.app.Application
import com.example.firstworktask.MainActivity
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.main.MainViewModel
import com.example.firstworktask.second.SecondViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitInstance::class, ViewModelModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<MyApplication> {


    fun getMainViewModel() : MainViewModel
    fun getSecondViewModel() : SecondViewModel

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