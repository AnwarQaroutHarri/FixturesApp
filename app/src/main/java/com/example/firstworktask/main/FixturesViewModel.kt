package com.example.firstworktask.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstworktask.main.models.models.Response
import com.example.firstworktask.main.repository.FixtureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val fixtureRepository: FixtureRepository
    ) : ViewModel() {

    private var _fixtureRequiredFields = MutableStateFlow<List<Response>>(listOf())
    val fixtureRequiredFields
        get() = _fixtureRequiredFields


    fun getFixturesByDate(date: LocalDate) {
        viewModelScope.launch {
            _fixtureRequiredFields.value = fixtureRepository.getFixturesByDate(date)
        }
    }

}