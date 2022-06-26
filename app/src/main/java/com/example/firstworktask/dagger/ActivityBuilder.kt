package com.example.firstworktask.dagger

import com.example.firstworktask.SwipeViewActivity
import com.example.firstworktask.main.CalendarFragment
import com.example.firstworktask.main.FixturesFragment
import com.example.firstworktask.second.FixtureDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): SwipeViewActivity

    @ContributesAndroidInjector
    abstract fun contributeSecondActivity(): FixtureDetailsActivity

    @ContributesAndroidInjector
    abstract fun contributeCalendarFragment(): CalendarFragment

    @ContributesAndroidInjector
    abstract fun contributeFixturesFragment(): FixturesFragment

}