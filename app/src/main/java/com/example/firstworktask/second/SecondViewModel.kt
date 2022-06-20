package com.example.firstworktask.second

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import com.example.firstworktask.second.repository.FixtureDetailsRepository
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {
    val fixtureDetailsRepository = FixtureDetailsRepository()
   // var fixtureDetails : MutableLiveData<FixtureDetailsModel> = MutableLiveData()
    var fixtureDetailsData : MutableList<String> = mutableListOf()
    var fixtureDetails: MutableLiveData<MutableList<String>> = MutableLiveData()

    /*
    fun getFixtureDetailsById(id: Int){
        viewModelScope.launch {
            fixtureDetailsRepository.getFixtureDetailsById(fixtureDetails,id)
        }

    }

     */

    fun getFixtureDetailsByID(id: Int){
        viewModelScope.launch {
            fixtureDetailsRepository.getFixtureDetailsByID(fixtureDetailsData,id)
            fixtureDetails.postValue(fixtureDetailsData)
        }
    }
}