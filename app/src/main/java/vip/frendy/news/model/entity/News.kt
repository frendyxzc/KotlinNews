package vip.frendy.news.model.entity

/**
 * Created by iiMedia on 2017/6/2.
 */
data class RespGetNews (
        val code: Int,
        val msg: String,
        val data: ArrayList<News>
)

data class News(
        var title: String,
        var summary: String,
        var image: String,
        var image_type: String
)