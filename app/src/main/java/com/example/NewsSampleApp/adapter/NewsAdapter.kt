package com.example.NewsSampleApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.NewsSampleApp.model.Articles

class NewsAdapter(private val newsList:ArrayList<Articles>, private val listener: INewsAdapter): RecyclerView.Adapter<NewsAdapter.NewsViewHoder>() {


    inner class NewsViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.article_title)
        val article_image= itemView.findViewById<ImageView>(R.id.article_image)
        val description=  itemView.findViewById<TextView>(R.id.desc)
        val publishDate=  itemView.findViewById<TextView>(R.id.publish_date)
        val cardLayout= itemView.findViewById<ConstraintLayout>(R.id.news_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHoder {
        val viewHoder = NewsViewHoder(LayoutInflater.from(parent.context).
                        inflate(R.layout.news_item, parent, false))
        viewHoder.cardLayout.setOnClickListener {

            listener.onItemClicked(newsList[viewHoder.adapterPosition])
        }
        return viewHoder
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHoder, position: Int) {
        val newsItem = newsList[position]
        holder.title.text = newsItem.title
        holder.description.text = newsItem.description
        holder.publishDate.text = newsItem.publishedAt
        Glide.with(holder.itemView.context).load(newsItem.urlToImage).centerCrop().into(holder.article_image)


    }
}

interface INewsAdapter{
    fun onItemClicked(article: Articles)
}