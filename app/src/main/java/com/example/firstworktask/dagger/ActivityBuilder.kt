package com.example.firstworktask.dagger

import com.example.firstworktask.SwipeViewActivity
import com.example.firstworktask.main.CalendarFragment
import com.example.firstworktask.main.FirstDateFragment
import com.example.firstworktask.main.SecondDateFragment
import com.example.firstworktask.main.ThirdDateFragment
import com.example.firstworktask.second.SecondActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : SwipeViewActivity

    @ContributesAndroidInjector
    abstract fun contributeSecondActivity() : SecondActivity

    @ContributesAndroidInjector
    abstract fun contributeFirstDateFragment() : FirstDateFragment

    @ContributesAndroidInjector
    abstract fun contributeSecondDateFragment() : SecondDateFragment

    @ContributesAndroidInjector
    abstract fun contributeThirdDateFragment() : ThirdDateFragment

    @ContributesAndroidInjector
    abstract fun contributeCalendarFragment() : CalendarFragment

}