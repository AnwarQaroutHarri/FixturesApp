package com.example.firstworktask.main.models.FixtureModelPackage

data class FixtureModel(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)