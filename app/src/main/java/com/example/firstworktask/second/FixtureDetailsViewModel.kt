package com.example.firstworktask.second

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.second.repository.FixtureDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureDetailsViewModel @Inject constructor(
    private val fixtureDetailsRepository: FixtureDetailsRepository
    ): ViewModel() {

    private var _fixtureDetails = MutableStateFlow<MutableList<String>>(mutableListOf())

    val fixtureDetails
    get() = _fixtureDetails

    /* Send ID to repository and let it search for details with the given ID.
        Send the List to populate it, then post data of the list into the LiveData.
     */
    fun getFixtureDetailsByID(id: Int){
        viewModelScope.launch {
            val result = fixtureDetailsRepository.getFixtureDetailsByID(/*fixtureDetailsData,*/id)
            _fixtureDetails.value = result
        }
    }
}