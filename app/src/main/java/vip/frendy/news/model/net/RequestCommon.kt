package vip.frendy.news.model.net

import java.net.URL

/**
 * Created by iiMedia on 2017/6/2.
 */
class RequestCommon(val url: String) {

    companion object {
        val APP_ID = "5225"
        val APP_KEY = "9b836682f204ac0503980acd48b4df21"
        val APP_INFO = "app_id=" + APP_ID + "&app_key=" + APP_KEY

        val BASE_URL = "http://www.myxianwen.cn/newsapp/index.action?"
        val BASE_URL_2 = "http://api.myxianwen.cn/1"
        val INIT_URL = BASE_URL + APP_INFO + "&action=init&equip_type=0&params=";
        val GET_CHANNEL = BASE_URL_2 + "/user/getchannellist?" + APP_INFO + "&news_type=0&equip_type=0&version=3.6.2"
        val GET_NEWS_LIST = BASE_URL_2 + "/news/getlist?" + APP_INFO + "&equip_type=0&updown=0&version=3.6.2"
    }

    fun run(): String {
        val jsonStr = URL(url).readText()
        return jsonStr
    }
}