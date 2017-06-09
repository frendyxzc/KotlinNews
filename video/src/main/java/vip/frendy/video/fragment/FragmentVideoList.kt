package vip.frendy.video.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_video_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vip.frendy.model.entity.News
import vip.frendy.model.net.Request
import vip.frendy.model.router.Router
import vip.frendy.video.R
import vip.frendy.video.adapter.VideoListAdapter


/**
 * 视频列表页
 */
class FragmentVideoList : Fragment(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private var rootView: View? = null
    private var uid: String = "0"
    private var cid: String = "0"
    private var mVideoList: ArrayList<News> = ArrayList()

    companion object {
        fun getInstance(bundle: Bundle): FragmentVideoList {
            val fragment = FragmentVideoList()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_video_list, container, false)
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
        loadVideo(uid, cid)
    }

    private fun initView() {
        val swipeRefreshLayout = rootView?.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)

        val videoList = rootView?.findViewById(R.id.videoList) as RecyclerView
        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        videoList.layoutManager = mLayoutManager
        videoList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun loadVideo(uid: String, cid: String) = doAsync {
        val list = Request(activity).getVideoList(uid, cid)
        mVideoList.clear()
        mVideoList.addAll(list)
        uiThread {
            videoList.adapter = VideoListAdapter(mVideoList) {
                Router.intentToVideoDetail(
                        activity, it.url, it.title)
            }
        }
    }

    private fun loadMore(uid: String, cid: String) = doAsync {
        val list = Request(activity).getVideoList(uid, cid)
        mVideoList.addAll(list)
        uiThread {
            videoList.adapter.notifyDataSetChanged()
        }
    }

    private fun refresh(uid: String, cid: String) = doAsync {
        val list = Request(activity).getVideoList(uid, cid)
        mVideoList.addAll(0, list)
        uiThread {
            videoList.adapter.notifyDataSetChanged()
            swipeRefreshLayout?.isRefreshing = false
        }
    }
}
