package vip.frendy.news.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import vip.frendy.news.R
import vip.frendy.news.activity.DetailActivity
import vip.frendy.news.model.data.Constants


/**
 * 关于我
 */
class FragmentAbout : Fragment(), View.OnClickListener, View.OnLongClickListener {
    private var rootView: View? = null

    companion object {
        fun getInstance(): FragmentAbout {
            val fragment = FragmentAbout()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_about, container, false)
            initData()
            initView()
        }
        // 缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误
        (rootView?.parent as? ViewGroup)?.removeView(rootView)
        return rootView
    }

    private fun initData() {

    }

    private fun initView() {
        rootView?.findViewById(R.id.intro)?.setOnClickListener(this)
        rootView?.findViewById(R.id.card)?.setOnLongClickListener(this)
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
        when(view.id) {
            R.id.intro -> activity.startActivity<DetailActivity>(
                    DetailActivity.URL to Constants.DEFAULT_URL)
        }
    }

    override fun onLongClick(view: View): Boolean {
        when(view.id) {
            R.id.card -> {
                // 外部浏览器打开微信公众链接会触发下载，不能导流
                activity.toast("请用微信扫描二维码，或者在微信内搜索 frendy-share")
            }
        }
        return true
    }
}
