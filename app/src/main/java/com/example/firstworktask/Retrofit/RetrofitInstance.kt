package com.example.firstworktask.Retrofit

import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstance {

    @Singleton
    @Provides
    fun provideRetrofitInstance() : FixtureAPI {
        val interceptor = HttpLoggingInterceptor()
         val client = OkHttpClient.Builder().apply {
            interceptor.level = (HttpLoggingInterceptor.Level.BODY)
            addInterceptor(FixtureInterceptor())
            addInterceptor(interceptor)
        }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FixtureAPI::class.java)
    }

}