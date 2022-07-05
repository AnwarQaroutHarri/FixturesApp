package com.example.firstworktask.main.models.models

data class Fixture(
    var date: String,
    val id: Int,
    val periods: Periods,
    val referee: String,
    val status: Status,
    val timestamp: Int,
    val timezone: String,
    val venue: Venue
)