package com.example.NewsSampleApp

import android.util.Log
import com.example.NewsSampleApp.model.Articles
import com.example.NewsSampleApp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDao(val listener: ISuccessListener) {
//comment added by KetanBhangale04 User
    fun getNews(){
        val news: Call<News> = NewsService.retrofitInstance.getHeadLines("in", 1)
        Log.i("url","${news.request().toString()}")
        news.enqueue(object: Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.i("news failed","${t.message}")
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.i("news Success","${response.body().toString()}")
                val news: News? = response.body()
                if (news != null){
                    listener.onNewsSuccess(news.articles)
                    //Log.i("news sucess response","${newsList.size}")
                }

            }

        })

    }
}

interface ISuccessListener{
    fun onNewsSuccess(news: ArrayList<Articles>)
}


