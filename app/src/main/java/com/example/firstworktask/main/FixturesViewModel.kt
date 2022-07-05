package com.example.firstworktask.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.models.Response
import com.example.firstworktask.main.repository.FixtureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val fixtureRepository: FixtureRepository
    ) : ViewModel() {

    private val _fixtureRequiredFields = MutableStateFlow<List<Response>>(listOf())
    val fixtureRequiredFields : StateFlow<List<Response>>
        get() = _fixtureRequiredFields

    private val _error = MutableStateFlow<String>("")
    val error : StateFlow<String>
    get() = _error


    fun getFixturesByDate(date: LocalDate) {
        try {
            viewModelScope.launch {
                _fixtureRequiredFields.value = fixtureRepository.getFixturesByDate(date)
                if(_fixtureRequiredFields.value.isEmpty()) {
                    _error.value = "No fixtures in that day."
                }
            }
        } catch (ex: Exception){
            _error.value = "An error occurred. Please try reloading."

        }
    }

}