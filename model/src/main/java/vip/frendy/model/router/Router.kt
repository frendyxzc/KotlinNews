package vip.frendy.model.router

import android.app.Activity
import android.content.Intent

/**
 * Created by frend on 2017/6/8.
 */
object Router {
    val NEWS_DETAIL_ACTIVITY = "vip.frendy.news.DetailActivity"
    val NEWS_URL: String = "url"

    val VIDEO_DETAIL_ACTIVITY = "vip.frendy.video.DetailActivity"
    val VIDEO_PATH: String = "videoPath"
    val VIDEO_TITLE: String = "videoTitle"

    fun intentToNewsDetail(activity: Activity, url: String) {
        val intent = Intent(NEWS_DETAIL_ACTIVITY)
        intent.putExtra(NEWS_URL, url)
        activity.startActivity(intent)
    }

    fun intentToVideoDetail(activity: Activity, path: String, title: String) {
        val intent = Intent(VIDEO_DETAIL_ACTIVITY)
        intent.putExtra(VIDEO_PATH, path)
        intent.putExtra(VIDEO_TITLE, title)
        activity.startActivity(intent)
    }
}