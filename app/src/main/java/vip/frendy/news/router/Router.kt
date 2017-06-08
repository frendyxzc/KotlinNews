package vip.frendy.news.router

import android.app.Activity
import android.content.Intent

/**
 * Created by frend on 2017/6/8.
 */
object Router {

    fun intentToVideoDetail(activity: Activity, path: String, title: String) {
        val intent = Intent("me.frendy.video.DetailActivity")
        intent.putExtra(me.frendy.video.DetailActivity.PATH, path)
        intent.putExtra(me.frendy.video.DetailActivity.TITLE, title)
        activity.startActivity(intent)
    }
}