package vip.frendy.news_m.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import vip.frendy.model.entity.News
import vip.frendy.news_m.R

class NewsListAdapter(val list: ArrayList<News>, val itemClickListener: (News) -> Unit): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        when(viewType) {
            TYPE_NO_IMG -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_no_img, parent, false)
                return ViewHolderNoImg(view, itemClickListener)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
                return ViewHolderCard(view, itemClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        when(list[position].image_type) {
            "0" -> return TYPE_NO_IMG
            else -> return TYPE_CARD
        }
    }

    /**
     * ViewHolder impl
     */
    val TYPE_NO_IMG = 0
    val TYPE_CARD = 1

    open class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        open fun bind(news: News) { }
    }

    class ViewHolderNoImg(view: View, val itemClickListener: (News) -> Unit): ViewHolder(view) {
        override fun bind(news: News) {
            with(news) {
                itemView.title.text = news.title
                if(!TextUtils.equals(news.summary, "")) {
                    itemView.desc.text = news.summary
                } else itemView.desc.visibility = GONE
                itemView.setOnClickListener { itemClickListener(this) }
            }
        }
    }

    class ViewHolderCard(view: View, val itemClickListener: (News) -> Unit): ViewHolder(view) {
        override fun bind(news: News) {
            with(news) {
                itemView.title.text = news.title
                if(!TextUtils.equals(news.summary, "")) {
                    itemView.desc.text = news.summary
                } else itemView.desc.visibility = GONE
                Glide.with(itemView.context).load(news.image).fitCenter().into(itemView.image)
                itemView.setOnClickListener { itemClickListener(this) }
            }
        }
    }
}