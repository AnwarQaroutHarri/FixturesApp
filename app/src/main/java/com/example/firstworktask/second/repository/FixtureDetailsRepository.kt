package com.example.firstworktask.second.repository

import com.example.firstworktask.api.FixtureAPI
import javax.inject.Inject

class FixtureDetailsRepository @Inject constructor() {

    @Inject
    lateinit var retrofitInstance: FixtureAPI

    /*
    get fixture events by ID, populate them into the list.
     */
    suspend fun getFixtureDetailsByID(
        id: Int
    ) : MutableList<String> {

        val strList : MutableList<String> = mutableListOf()
        val str = StringBuilder()

        val response = retrofitInstance.getFixtureDetailsById(id).response[0].events

        /* Filter response events in a StringBuilder in an appropriate way */
            for (event in response) {
                str.append(event.time.elapsed.toString() + ": ")

                if (event.type == "Card") {
                    str.append(event.detail)
                    str.append(" - ${event.player.name} (${event.team.name})")
                } else if (event.type == "Goal") {
                    if (event.assist.name.isNotEmpty()) {
                        str.append("Goal ${event.player.name} assist: ${event.assist.name} (${event.team.name}")
                    } else {
                        str.append("Goal ${event.player.name} (${event.team.name})")
                    }
                } else if (event.type == "subst") {
                    str.append("Substitution (${event.team.name}): ${event.assist.name} -> ${event.player.name}")
                }

                strList.add(str.toString())
                str.clear()
            }

        return strList
    }
}