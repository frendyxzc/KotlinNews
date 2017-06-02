package vip.frendy.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import vip.frendy.news.adapter.NewsListAdapter
import vip.frendy.news.model.News
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadNews()
    }

    private fun loadNews() = doAsync {
        val list = mockData()
        uiThread {
            newsList.adapter = NewsListAdapter(list) {
                toast(it.title)
            }
        }
    }

    private fun mockData(): ArrayList<News> {
        val list = ArrayList<News>()
        for(i in 0..20) {
            list.add(News("Test Title $i", "Test Desc $i"))
        }
        return list
    }
}

