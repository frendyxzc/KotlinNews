package vip.frendy.news.model.net

import android.content.Context
import com.google.gson.Gson
import vip.frendy.news.model.entity.ReqInit
import vip.frendy.news.model.entity.RespInit
import vip.frendy.news.model.entity.UserID
import vip.frendy.news.util.DeviceInfo

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
}