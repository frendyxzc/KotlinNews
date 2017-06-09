package vip.frendy.news_m.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news_content.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vip.frendy.model.net.Request
import vip.frendy.news_m.R


/**
 * 新闻内容页
 */
class FragmentNewsContent : Fragment(), View.OnClickListener {
    private var rootView: View? = null
    private var uid: String = "0"
    private val mFragments: ArrayList<FragmentNewsList> = ArrayList()
    private val mTitles: ArrayList<String> = ArrayList()

    companion object {
        fun getInstance(bundle: Bundle): FragmentNewsContent {
            val fragment = FragmentNewsContent()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_news_content, container, false)
            initData(arguments)
            initView()
        }
        // 缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误
        (rootView?.parent as? ViewGroup)?.removeView(rootView)
        return rootView
    }

    private fun initData(args: Bundle) {
        uid = args.getString("uid")
        getChannel(uid)
    }

    private fun initView() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {

        } else {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {

    }

    private fun getChannel(uid: String) = doAsync {
        val channelList = Request(context).getChannelList(uid)
        uiThread {
            for (channel in channelList) {
                val args: Bundle = Bundle()
                args.putString("uid", uid)
                args.putString("cid", channel.id)
                mFragments.add(FragmentNewsList.getInstance(args))
                mTitles.add(channel.name)
            }

            content.adapter = NewsPagerAdapter(childFragmentManager)
            tabs.setViewPager(content)
        }
    }

    inner class NewsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = mFragments.size
        override fun getItem(index: Int): Fragment = mFragments[index]
        override fun getPageTitle(index: Int): CharSequence = mTitles[index]
    }
}
