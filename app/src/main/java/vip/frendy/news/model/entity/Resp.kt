package vip.frendy.news.model.entity

/**
 * Created by iiMedia on 2017/6/2.
 */
data class ReqInit (
        val imei: String
)

data class RespInit (
        val code: Int,
        val msg: String,
        val data: vip.frendy.news.model.entity.UserID
)

data class UserID (
        val user_id: String
)


data class RespGetChannel (
        val code: Int,
        val msg: String,
        val data: ChannelList
)

data class ChannelList (
        val channel_list: ArrayList<Channel>
)

data class Channel (
        val id: String,
        val name: String
)