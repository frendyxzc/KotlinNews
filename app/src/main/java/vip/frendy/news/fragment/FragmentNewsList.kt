package vip.frendy.news.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import vip.frendy.news.R
import vip.frendy.news.adapter.NewsListAdapter
import vip.frendy.news.model.net.Request


/**
 * 直播列表页面
 */
class FragmentNewsList : Fragment(), View.OnClickListener {
    private var rootView: View? = null

    companion object {
        fun getInstance(bundle: Bundle): FragmentNewsList {
            val fragment = FragmentNewsList()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_news_list, container, false)
            initData(arguments)
            initView()
        }
        // 缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误
        (rootView?.parent as? ViewGroup)?.removeView(rootView)
        return rootView
    }

    private fun initData(args: Bundle) {
        loadNews(args.getString("uid"), args.getString("cid"))
    }

    private fun initView() {
        val newsList = rootView?.findViewById(R.id.newsList) as RecyclerView
        newsList.layoutManager = LinearLayoutManager(activity)
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

    private fun loadNews(uid: String, cid: String) = doAsync {
        val list = Request(activity).getNewsList(uid, cid)
        uiThread {
            newsList.adapter = NewsListAdapter(list) {
                activity.toast(it.title)
            }
        }
    }
}
