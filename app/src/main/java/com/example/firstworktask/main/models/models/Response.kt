package com.example.firstworktask.main.models.models

data class Response(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
)