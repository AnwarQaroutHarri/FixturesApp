package com.example.firstworktask.main.repository

import android.graphics.BitmapFactory
import android.text.TextUtils.indexOf
import androidx.lifecycle.MutableLiveData
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

/*

 */

class FixtureRepository {
    lateinit var fixturesBetweenTwoDates : List<Response>
    /*
    val fixtureId: Int = 0
    val homeTeamName: String = ""
    val homeTeamLogo: String = ""
    val homeTeamScore: Int = 0
    val awayTeamName: String = ""
    val awayTeamLogo: String = ""
    val awayTeamScore: Int = 0

     */
    /*
    suspend fun getFixturesForSeasonBetweenTwoDates() : FullFixtureResponse {
        return RetrofitInstance.fixtureAPI.getFixturesForSeasonBetweenTwoDates()
    }

     */



   /* private suspend fun getFixturesForSeasonBetweenTwoDates() {

    }

    */

    suspend fun getRequiredFixtures() : List<Response> {
        fixturesBetweenTwoDates = RetrofitInstance.fixtureAPI.getFixturesForSeasonBetweenTwoDates().response
        withContext(Dispatchers.Default){
            for(element in fixturesBetweenTwoDates){
                element.fixture.date = element.fixture.date.replace("T","\n")
                element.fixture.date = element.fixture.date.replaceAfter("+","")
            }
        }
        //println("------- IN REPO $fixturesBetweenTwoDates")
        /*
        val fixtureRequiredFields: MutableList<FixtureRequiredFields> = mutableListOf()
        withContext(Dispatchers.Default) {
            fixturesBetweenTwoDates.forEach { item ->
                fixtureRequiredFields.add(
                    FixtureRequiredFields(
                        item.fixture.id,
                        item.teams.home.name,
                        item.teams.home.logo,
                        item.score.fulltime.home,
                        item.teams.away.name,
                        item.teams.away.logo,
                        item.score.fulltime.away
                    )
                )

            }

         */
        return fixturesBetweenTwoDates
    }

    /*
    suspend fun getBitmaps(fixturesRequiredFields: MutableLiveData<List<Response>>) {
        withContext(Dispatchers.IO){
            for (element in fixturesRequiredFields.value!!) {
               // println("OKAY LETS SEE ${element.teams.home.logo}")
                element.teams.home.logoImage = BitmapFactory.decodeStream(
                    URL(element.teams.home.logo).openConnection().getInputStream()
                )
                element.teams.away.logoImage = BitmapFactory.decodeStream(
                    URL(element.teams.away.logo).openConnection().getInputStream()
                )
            }
        }
    }

     */


}



