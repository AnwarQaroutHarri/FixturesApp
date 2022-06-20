package com.example.firstworktask.second.models.FixtureDetailsPackage

data class FixtureDetailsModel(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)