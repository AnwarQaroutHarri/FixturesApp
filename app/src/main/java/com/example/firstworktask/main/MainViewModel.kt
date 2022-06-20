package com.example.firstworktask.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.main.repository.FixtureRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val fixtureRepository = FixtureRepository()

    var fixtureRequiredFields: MutableLiveData<List<Response>> = MutableLiveData()

    /*
    var homeBitMaps: MutableList<Bitmap> = mutableListOf()
    var awayBitMaps: MutableList<Bitmap> = mutableListOf()

     */


    init {
        viewModelScope.launch {
            fixtureRequiredFields.value = fixtureRepository.getRequiredFixtures()
         //   initializeBitmaps()


        }
    }





    fun getFixtures() {
        viewModelScope.launch {
            fixtureRequiredFields.value = fixtureRepository.getRequiredFixtures()
        }

    }




    /*
     suspend fun initializeBitmaps() {

         fixtureRepository.getBitmaps(fixtureRequiredFields)
       /* withContext(Dispatchers.IO) {
                for (element in fixtureRequiredFields.value!!) {
                    println("OKAY LETS SEE ${element.teams.home.logo}")
                    homeBitMaps.add(
                        BitmapFactory.decodeStream(
                            URL(element.teams.home.logo).openConnection().getInputStream()
                        )
                    )
                    awayBitMaps.add(
                        BitmapFactory.decodeStream(
                            URL(element.teams.away.logo).openConnection().getInputStream()
                        )
                    )

                }
            }

        */

    }

     */


}