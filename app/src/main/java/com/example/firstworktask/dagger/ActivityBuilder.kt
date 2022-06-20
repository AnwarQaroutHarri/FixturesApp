package com.example.firstworktask.dagger

import com.example.firstworktask.MainActivity
import com.example.firstworktask.second.SecondActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector
    abstract fun contributeSecondActivity() : SecondActivity


}