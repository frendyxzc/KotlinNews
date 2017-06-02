package vip.frendy.news.model.net

import java.net.URL

/**
 * Created by iiMedia on 2017/6/2.
 */
class RequestCommon(val url: String) {

    companion object {
        val APP_ID = "5209"
        val APP_KEY = "6289c8b516abd5107d54524806d4f020"
        val APP_INFO = "app_id=" + APP_ID + "&app_key=" + APP_KEY

        val BASE_URL = "http://www.myxianwen.cn/newsapp/index.action?"
        val INIT_URL = BASE_URL + APP_INFO + "&action=init&equip_type=0&params=";
    }

    fun run(): String {
        val jsonStr = URL(url).readText()
        return jsonStr
    }
}