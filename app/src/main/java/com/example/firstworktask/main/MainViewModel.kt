package com.example.firstworktask.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.main.repository.FixtureRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fixtureRepository: FixtureRepository): ViewModel() {


    var fixtureRequiredFields: MutableLiveData<List<Response>> = MutableLiveData()

    /*
    On initialization, fetch the fextures from the repository and populate them in the livedata.
     */
    init {
        viewModelScope.launch {
            fixtureRequiredFields.value = fixtureRepository.getRequiredFixtures()
        }
    }

}