package vip.frendy.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import vip.frendy.news.R
import vip.frendy.news.model.entity.News

class NewsListAdapter(val list: ArrayList<News>, val itemClickListener: (News) -> Unit): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View, val itemClickListener: (News) -> Unit): RecyclerView.ViewHolder(view) {
        fun bind(news: News) {
            with(news) {
                itemView.title.text = news.title
                itemView.desc.text = news.summary
                Glide.with(itemView.context).load(news.image).into(itemView.image)
                itemView.setOnClickListener { itemClickListener(this) }
            }
        }
    }
}