package com.example.firstworktask.main.repository

import android.graphics.BitmapFactory
import android.text.TextUtils.indexOf
import androidx.lifecycle.MutableLiveData
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class FixtureRepository @Inject constructor(){
    @Inject
    lateinit var retrofitInstance: FixtureAPI

    suspend fun getFixturesByDate(date: String) : List<Response> {
        val fixtures = retrofitInstance.getFixturesByDate(date).response
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            for(element in fixtures){
                val offsetDateTime = OffsetDateTime.parse(element.fixture.date)
                element.fixture.date = offsetDateTime.format(formatter)
            }
        return fixtures
    }

}



