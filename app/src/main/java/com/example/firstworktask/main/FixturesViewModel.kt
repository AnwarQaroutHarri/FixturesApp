package com.example.firstworktask.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.main.repository.FixtureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val fixtureRepository: FixtureRepository
    ) : ViewModel() {

    private var _fixtureRequiredFields = MutableStateFlow<List<Response>>(listOf())
    val fixtureRequiredFields
        get() = _fixtureRequiredFields


    fun getFixturesByDate(date: String) {
        viewModelScope.launch {
            _fixtureRequiredFields.value = fixtureRepository.getFixturesByDate(date)
        }
    }

}