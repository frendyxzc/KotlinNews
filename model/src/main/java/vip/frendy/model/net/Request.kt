package vip.frendy.model.net

import android.content.Context
import com.google.gson.Gson
import vip.frendy.model.entity.*
import vip.frendy.model.util.DeviceInfo

/**
 * Created by iiMedia on 2017/6/2.
 */
class Request(val context: Context, val gson: Gson = Gson()) {

    fun init(): UserID {
        val params: ReqInit = ReqInit(DeviceInfo.getAndroidID(context))
        val url = RequestCommon.INIT_URL + gson.toJson(params)

        val jsonStr = RequestCommon(url).run()
        return gson.fromJson(jsonStr, RespInit::class.java).data
    }

    fun getChannelList(uid: String, type: Int = 0): ArrayList<Channel> {
        val url = RequestCommon.GET_CHANNEL + "&timestamp=" + System.currentTimeMillis() + "&uid=" + uid + "&news_type=" + type

        val jsonStr = RequestCommon(url).run()
        return gson.fromJson(jsonStr, RespGetChannel::class.java).data.channel_list
    }

    fun getNewsList(uid: String, cid: String): ArrayList<News> {
        val url = RequestCommon.GET_NEWS_LIST + "&uid=" + uid + "&type_id=" + cid

        val jsonStr = RequestCommon(url).run()
        return gson.fromJson(jsonStr, RespGetNews::class.java).data
    }

    fun getVideoList(uid: String, cid: String): ArrayList<News> {
        val url = RequestCommon.GET_VIDEO_LIST + "&uid=" + uid + "&type_id=" + cid

        val jsonStr = RequestCommon(url).run()
        return gson.fromJson(jsonStr, RespGetNews::class.java).data
    }
}