package com.example.NewsSampleApp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "7a0070dd915247bab7d4bf6f34264557"
interface IRetrofit{
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country") country: String, @Query("page")page: Int): Call<News>
}

object NewsService{
    val retrofitInstance: IRetrofit
    init {
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofitInstance = retrofit.create(IRetrofit::class.java)
    }

}