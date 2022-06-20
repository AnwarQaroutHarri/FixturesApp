package com.example.firstworktask.Retrofit

import okhttp3.Interceptor
import okhttp3.Response

class FixtureInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key","6f61331ce8msh79de5f5fa75e1bcp111aebjsn1ea95e4cbcad")
            .addHeader("X-RapidAPI-Host","api-football-v1.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }

}