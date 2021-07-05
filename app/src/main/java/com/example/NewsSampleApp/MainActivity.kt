package com.example.NewsSampleApp

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NewsSampleApp.model.Articles
import com.example.NewsSampleApp.model.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), INewsAdapter, ISuccessListener {
    lateinit var adapter: NewsAdapter
    lateinit var newsDao: NewsDao
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity


            newsDao = NewsDao(this)
        progressBar.visibility = View.VISIBLE
            newsDao.getNews()
       // adapter = NewsAdapter(newsDao.getNews(), this)
//        val scope = CoroutineScope(Dispatchers.Main)
//        var newsList:ArrayList<Articles> = newsDao.getNews()
//        scope.launch {
//            withContext(Dispatchers.IO) {
//                newsDao.getNews(context)
//                Log.i("news count", "${newsList.size}")
//                Log.i("news UI work", "${newsList.size}")
//            }
//
//        }
//    GlobalScope.launch(Dispatchers.IO) {
//        val newsList:ArrayList<Articles> = newsDao.getNews()
//        Log.i("news count","${newsList.size}")
//        withContext(Dispatchers.Main){
//            Log.i("news UI work","${newsList.size}")
//        }
//    }




    }

    override fun onItemClicked(article: Articles) {
        val coolorInt: Int = Color.parseColor("#FF6200EE") //red
        val builder = CustomTabsIntent.Builder();
        builder.setToolbarColor(coolorInt)
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(article.url));
    }

private fun  getNews(context: Context){
    val news: Call<News> = NewsService.retrofitInstance.getHeadLines("in", 1)
    Log.i("url","${news.request().toString()}")
    news.enqueue(object: Callback<News> {
        override fun onFailure(call: Call<News>, t: Throwable) {
            Log.i("failed", "${t.message}")
        }

        override fun onResponse(call: Call<News>, response: Response<News>) {
            Log.i("Success", "${response.body().toString()}")
            val news: News? = response.body()
            if (news != null) {

               // adapter = NewsAdapter(news.articles, context)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
            }
        }
    })
}

    override fun onNewsSuccess(news: ArrayList<Articles>) {
        Log.i("news count in success","${news.size}")
        progressBar.visibility = View.GONE
        adapter = NewsAdapter(news, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

}