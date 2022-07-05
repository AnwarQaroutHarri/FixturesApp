package com.example.firstworktask.main.repository

import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FixtureRepository @Inject constructor(){
    
    @Inject
    lateinit var retrofitInstance: FixtureAPI

    suspend fun getFixturesByDate(date: LocalDate) : List<Response> {
        val fixtures = retrofitInstance.getFixturesByDate(date).response
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            for(element in fixtures){
                val offsetDateTime = OffsetDateTime.parse(element.fixture.date)
                element.fixture.date = offsetDateTime.format(formatter)
            }
        return fixtures
    }

}



