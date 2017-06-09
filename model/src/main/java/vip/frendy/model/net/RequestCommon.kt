package vip.frendy.model.net

import vip.frendy.model.data.Constants
import java.net.URL

/**
 * Created by iiMedia on 2017/6/2.
 */
class RequestCommon(val url: String) {

    companion object {
        val BASE_URL = "http://www.myxianwen.cn/newsapp/index.action?"
        val BASE_URL_2 = "http://api.myxianwen.cn/1"
        val INIT_URL = BASE_URL + Constants.APP_INFO + "&action=init&equip_type=0&params=";
        val GET_CHANNEL = BASE_URL_2 + "/user/getchannellist?" + Constants.APP_INFO + "&equip_type=0&version=3.6.2"
        val GET_NEWS_LIST = BASE_URL_2 + "/news/getlist?" + Constants.APP_INFO + "&equip_type=0&updown=0&version=3.6.2"
        val GET_VIDEO_LIST = BASE_URL_2 + "/news/getvediolist?" + Constants.APP_INFO + "&equip_type=0&updown=0&version=3.6.2"
    }

    fun run(): String {
        val jsonStr = URL(url).readText()
        return jsonStr
    }
}