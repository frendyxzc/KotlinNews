package vip.frendy.news.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vip.frendy.news.R


/**
 * 关于我
 */
class FragmentAbout : Fragment(), View.OnClickListener {
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
}
