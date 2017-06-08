package vip.frendy.news.model.entity

import vip.frendy.news.model.data.Constants

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
        var image_type: String,
        var news_id: String = "1000",
        var sn: String = "2D879AE7DAAB0E06210EEE2FE7D04B1CCDA4A914209DBF7203F03A7C48FA1773A6E1C60A17CD9A653CF438F9D75CCB82",
        var url: String = Constants.DEFAULT_URL,
        var skip_way: Int = 0,
        // video
        var target_url: String = ""
)

fun News.getUrl(): String {
    return Constants.XW_BASE_URL + Constants.APP_INFO + "&news_id=" + news_id + "&sn=" + sn + "&equip_type=0"
}