package vip.frendy.news

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.frendy.advertisement.AdHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vip.frendy.news.fragment.FragmentNewsList
import vip.frendy.news.model.entity.UserID
import vip.frendy.news.model.net.Request

class MainActivity : AppCompatActivity() {
    private val mContext: Context = this
    private val mFragments: ArrayList<FragmentNewsList> = ArrayList()
    private val mTitles: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdHelper.getInstance(mContext).requestPermissions(this)
        initUser()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        AdHelper.getInstance(mContext).onRequestPermissionsResult(requestCode, permissions, grantResults)
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
            for (channel in channelList) {
                val args: Bundle = Bundle()
                args.putString("uid", userID.user_id)
                args.putString("cid", channel.id)
                mFragments.add(FragmentNewsList.getInstance(args))
                mTitles.add(channel.name)
            }

            content.adapter = NewsPagerAdapter(supportFragmentManager)
            tabs.setViewPager(content)
        }
    }

    inner class NewsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = mFragments.size
        override fun getItem(index: Int): Fragment = mFragments[index]
        override fun getPageTitle(index: Int): CharSequence = mTitles[index]
    }
}



