package com.example.firstworktask.second.repository

import androidx.lifecycle.MutableLiveData
import com.example.firstworktask.Retrofit.RetrofitInstance
import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import javax.inject.Inject

class FixtureDetailsRepository @Inject constructor() {
    @Inject
    lateinit var retrofitInstance: FixtureAPI

    /*
    get fixture events by ID, populate them into the list.
     */
    suspend fun getFixtureDetailsByID(strList: MutableList<String>, id: Int) {
        val str = StringBuilder()
        val response = retrofitInstance.getFixtureDetailsById(id).response[0].events
        for(event in response){
            str.append(event.time.elapsed.toString() + ": ")
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
            strList.add(str.toString())
            str.clear()
        }
    }
}