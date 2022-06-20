package com.example.firstworktask.Retrofit

import com.example.firstworktask.api.FixtureAPI
import com.example.firstworktask.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitInstance {

    @Singleton
    @Provides
    fun provideRetrofitInstance() : FixtureAPI {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
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
    /*
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build()
    }

     val fixtureAPI : FixtureAPI by lazy {
        retrofit.create(FixtureAPI::class.java)
    }

     */

}