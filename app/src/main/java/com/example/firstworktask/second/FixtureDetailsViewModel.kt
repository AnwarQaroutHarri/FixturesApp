package com.example.firstworktask.second

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.repository.FixtureRepository
import com.example.firstworktask.second.models.FixtureDetailsPackage.FixtureDetailsModel
import com.example.firstworktask.second.repository.FixtureDetailsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FixtureDetailsViewModel @Inject constructor(private val fixtureDetailsRepository: FixtureDetailsRepository): ViewModel() {
    private var fixtureDetailsData : MutableList<String> = mutableListOf()
    private var _fixtureDetails: MutableLiveData<MutableList<String>> = MutableLiveData()

    val fixtureDetails
    get() = _fixtureDetails

    /* Send ID to repository and let it search for details with the given ID.
        Send the List to populate it, then post data of the list into the LiveData.
     */
    fun getFixtureDetailsByID(id: Int){
        viewModelScope.launch {
            fixtureDetailsRepository.getFixtureDetailsByID(fixtureDetailsData,id)
            _fixtureDetails.postValue(fixtureDetailsData)
        }
    }
}