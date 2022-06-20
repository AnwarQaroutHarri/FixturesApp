package com.example.firstworktask.api

import com.example.firstworktask.main.models.FixtureModelPackage.FixtureModel
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel

import retrofit2.http.GET
import retrofit2.http.Query

interface FixtureAPI {
    @GET("fixtures?league=39&season=2021&from=2020-05-07&to=2022-01-28")
    suspend fun getFixturesForSeasonBetweenTwoDates() : FixtureModel

    @GET("fixtures")
    suspend fun getFixtureDetailsById(@Query("id") id: Int) : FixtureDetailsModel



}