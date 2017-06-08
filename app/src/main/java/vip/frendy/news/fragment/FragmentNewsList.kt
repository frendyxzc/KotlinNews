package vip.frendy.news.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qq.e.ads.nativ.NativeADDataRef
import kotlinx.android.synthetic.main.fragment_news_list.*
import me.frendy.advertisement.AdHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import vip.frendy.news.R
import vip.frendy.news.activity.DetailActivity
import vip.frendy.news.adapter.NewsListAdapter
import vip.frendy.news.model.entity.News
import vip.frendy.news.model.entity.getUrl
import vip.frendy.news.model.net.Request
import vip.frendy.news.router.Router


/**
 * 新闻列表页
 */
class FragmentNewsList : Fragment(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private var rootView: View? = null
    private var uid: String = "0"
    private var cid: String = "0"
    private var mNewsList: ArrayList<News> = ArrayList()

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
        uid = args.getString("uid")
        cid = args.getString("cid")
        loadNews(uid, cid)
    }

    private fun initView() {
        val swipeRefreshLayout = rootView?.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)

        val newsList = rootView?.findViewById(R.id.newsList) as RecyclerView
        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        newsList.layoutManager = mLayoutManager
        newsList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = mLayoutManager.getChildCount()
                val totalItemCount = mLayoutManager.getItemCount()
                val pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisiblesItems >= totalItemCount - 3) {
                    loadMore(uid, cid)
                }
            }
        })
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

    override fun onRefresh() {
        refresh(uid, cid)
    }

    private fun loadNews(uid: String, cid: String) = doAsync {
        val list = Request(activity).getNewsList(uid, cid)
        mNewsList.clear()
        mNewsList.addAll(insertAd(list))
        uiThread {
            newsList.adapter = NewsListAdapter(mNewsList) {
                if(TextUtils.equals(it.image_type, "7")) {
                    activity.toast(it.title)
                } else {
                    when(it.skip_way) {
                        1 -> Router.intentToVideoDetail(
                                activity, it.target_url, it.title)
                        else -> activity.startActivity<DetailActivity>(
                                DetailActivity.URL to it.getUrl())
                    }
                }
            }
        }
    }

    private fun loadMore(uid: String, cid: String) = doAsync {
        val list = Request(activity).getNewsList(uid, cid)
        mNewsList.addAll(insertAd(list))
        uiThread {
            newsList.adapter.notifyDataSetChanged()
        }
    }

    private fun refresh(uid: String, cid: String) = doAsync {
        val list = Request(activity).getNewsList(uid, cid)
        mNewsList.addAll(0, insertAd(list))
        uiThread {
            newsList.adapter.notifyDataSetChanged()
            swipeRefreshLayout?.isRefreshing = false
        }
    }

    private fun insertAd(list: ArrayList<News>) : ArrayList<News> {
        val ad: NativeADDataRef ?= AdHelper.getInstance(context).getOneAd()
        if(ad != null) list.add(News(ad.title, ad.desc, ad.imgUrl, "7"))
        return  list
    }
}
