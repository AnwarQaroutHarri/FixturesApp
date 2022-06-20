package com.example.firstworktask.dagger

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {
    lateinit var appComponent: ApplicationComponent


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.builder().application(this).build()
        return appComponent
    }
}