package com.example.firstworktask.second.repository

import androidx.lifecycle.MutableLiveData
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import java.lang.StringBuilder

class FixtureDetailsRepository {

    /*
    suspend fun getFixtureDetailsById(
        fixtureDetails: MutableLiveData<FixtureDetailsModel>,
        id: Int
    ) {
        fixtureDetails.value = RetrofitInstance.fixtureAPI.getFixtureDetailsById(id)
        for(element in fixtureDetails.value!!.response) {
            println("AIGHT LETS SEE ${element.score.fulltime.home.toString()}")
        }
    }

     */


    suspend fun getFixtureDetailsByID(strList: MutableList<String>, id: Int) {
        val str = StringBuilder()
        val response = RetrofitInstance.fixtureAPI.getFixtureDetailsById(id).response[0].events
        for(event in response){
            println(event.time.elapsed.toString() + "-----1")
            str.append(event.time.elapsed.toString() + ": ")
           // println(str.toString() + "-----2")
            if(event.type.equals("Card")) {
                str.append(event.detail.toString())
                str.append(" - ${event.player.name.toString()} (${event.team.name})")
            }
            else if(event.type.equals("Goal")) {
                if(!event.assist.name.isNullOrEmpty())  {
                    str.append("Goal ${event.player.name} assist: ${event.assist.name} (${event.team.name.toString()}")
                }
                else {
                    str.append("Goal ${event.player.name} (${event.team.name.toString()})")
                }
            }
            else if(event.type.equals("subst")) {
                str.append("Substitution (${event.team.name.toString()}): ${event.assist.name} -> ${event.player.name}")
            }
            //str.append("\n")
            strList.add(str.toString())
            str.clear()
        }
    }
}