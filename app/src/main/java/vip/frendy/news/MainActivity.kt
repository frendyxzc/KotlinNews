package vip.frendy.news

import android.content.Context
import android.os.Bundle
import me.frendy.advertisement.AdHelper
import vip.frendy.news.activity.BaseFragmentActivity
import vip.frendy.news.fragment.FragmentNewsContent
import vip.frendy.news.model.data.DelegatesExt

class MainActivity : BaseFragmentActivity() {
    private val mContext: Context = this
    var uid: String by DelegatesExt.preference(this, "UID", "0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: Bundle = Bundle()
        args.putString("uid", uid)
        setDefaultFragment(FragmentNewsContent.getInstance(args))

        AdHelper.getInstance(mContext).requestPermissions(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        AdHelper.getInstance(mContext).onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}



