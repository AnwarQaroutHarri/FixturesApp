package com.example.firstworktask.main.repository

import android.graphics.BitmapFactory
import android.text.TextUtils.indexOf
import androidx.lifecycle.MutableLiveData
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

/*

 */

class FixtureRepository @Inject constructor(){
    @Inject
    lateinit var retrofitInstance: FixtureAPI

    lateinit var fixturesBetweenTwoDates : List<Response>

    /* Get the required fixtures from retrofit and return them as a list */
    /*
    suspend fun getRequiredFixtures() : List<Response> {
        fixturesBetweenTwoDates = retrofitInstance.getFixturesForSeasonBetweenTwoDates().response
        withContext(Dispatchers.Default){
            for(element in fixturesBetweenTwoDates){
                element.fixture.date = element.fixture.date.replace("T","\n")
                element.fixture.date = element.fixture.date.replaceAfter("+","")
            }
        }
        return fixturesBetweenTwoDates
    }

     */

    suspend fun getFixturesByDate(date: String) : List<Response> {
        val fixtures = retrofitInstance.getFixturesByDate(date).response
        withContext(Dispatchers.Default){
            for(element in fixtures){
                element.fixture.date = element.fixture.date.replace("T","\n")
                element.fixture.date = element.fixture.date.replaceAfter("+","")
            }
        }
        return fixtures
    }

}



