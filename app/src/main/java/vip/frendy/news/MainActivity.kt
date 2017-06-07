package vip.frendy.news

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.flyco.tablayout.entity.TabEntity
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.activity_main.*
import me.frendy.advertisement.AdHelper
import vip.frendy.news.fragment.FragmentAbout
import vip.frendy.news.fragment.FragmentNewsContent
import vip.frendy.news.model.data.DelegatesExt

class MainActivity : AppCompatActivity() {
    private val mContext: Context = this
    private val mFragments: ArrayList<Fragment> = ArrayList()

    private val mTitles: ArrayList<String> = arrayListOf("News", "About")
    private val mIconUnselectIds = intArrayOf(R.drawable.t_news, R.drawable.t_user)
    private val mIconSelectIds = intArrayOf(R.drawable.t_news_1, R.drawable.t_user_1)
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var uid: String by DelegatesExt.preference(this, "UID", "0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val args: Bundle = Bundle()
        args.putString("uid", uid)
        mFragments.add(FragmentNewsContent.getInstance(args))
        mFragments.add(FragmentAbout.getInstance())

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tabs.setTabData(mTabEntities, this, R.id.content, mFragments)

        AdHelper.getInstance(mContext).requestPermissions(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        AdHelper.getInstance(mContext).onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}



