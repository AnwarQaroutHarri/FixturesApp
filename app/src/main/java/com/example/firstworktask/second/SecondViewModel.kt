package com.example.firstworktask.second

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.repository.FixtureRepository
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import com.example.firstworktask.second.repository.FixtureDetailsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondViewModel @Inject constructor(private val fixtureDetailsRepository: FixtureDetailsRepository): ViewModel() {
    var fixtureDetailsData : MutableList<String> = mutableListOf()
    var fixtureDetails: MutableLiveData<MutableList<String>> = MutableLiveData()

    /* Send ID to repository and let it search for details with the given ID.
        Send the List to populate it, then post data of the list into the LiveData.
     */
    fun getFixtureDetailsByID(id: Int){
        viewModelScope.launch {
            fixtureDetailsRepository.getFixtureDetailsByID(fixtureDetailsData,id)
            fixtureDetails.postValue(fixtureDetailsData)
        }
    }
}