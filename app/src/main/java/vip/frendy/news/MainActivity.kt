package vip.frendy.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import vip.frendy.news.adapter.NewsListAdapter
import vip.frendy.news.model.entity.News
import vip.frendy.news.model.entity.UserID
import vip.frendy.news.model.net.Request

class MainActivity : AppCompatActivity() {
    val mContext = this;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList.layoutManager = LinearLayoutManager(mContext)
    }

    override fun onResume() {
        super.onResume()
        initUser()
    }

    private fun initUser() = doAsync {
        val userID = Request(mContext).init()
        uiThread {
            getChannel(userID)
        }
    }

    private fun getChannel(userID: UserID) = doAsync {
        val channelList = Request(mContext).getChannelList(userID.user_id)
        uiThread {
            loadNews(userID.user_id, channelList[0].id)
        }
    }

    private fun loadNews(uid: String, cid: String) = doAsync {
        val list = Request(mContext).getNewsList(uid, cid)
        uiThread {
            newsList.adapter = NewsListAdapter(list) {
                toast(it.title)
            }
        }
    }

    private fun mockData(): ArrayList<News> {
        val list = ArrayList<News>()
        for(i in 0..20) {
            list.add(News("Test Title $i", "Test Desc $i", ""))
        }
        return list
    }
}

