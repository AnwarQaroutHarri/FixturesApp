package com.example.firstworktask.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.main.repository.FixtureRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FixturesViewModel @Inject constructor(private val fixtureRepository: FixtureRepository) :
    ViewModel() {

    private var _fixtureRequiredFields: MutableLiveData<List<Response>> = MutableLiveData()
    val fixtureRequiredFields
        get() = _fixtureRequiredFields


    fun getFixturesByDate(date: String) {
        viewModelScope.launch {
            _fixtureRequiredFields.value = fixtureRepository.getFixturesByDate(date)
        }
    }

}