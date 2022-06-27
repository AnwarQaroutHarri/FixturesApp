package com.example.firstworktask.api

import com.example.firstworktask.main.models.FixtureModelPackage.FixtureModel
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

import retrofit2.http.GET
import retrofit2.http.Query

interface FixtureAPI {
    //fixtures?league=39&season=2022&from=2022-01-01&to=2022-12-12
    //fixtures?league=39&season=2021&from=2020-05-07&to=2022-01-28
    @GET("fixtures?league=39&season=2021&from=2020-05-07&to=2022-01-28")
    suspend fun getFixturesForSeasonBetweenTwoDates() : FixtureModel

    @GET("fixtures")
    suspend fun getFixtureDetailsById(@Query("id") id: Int) : FixtureDetailsModel

    @GET("fixtures")
    suspend fun getFixturesByDate(@Query("date") date: String) : FixtureModel
}