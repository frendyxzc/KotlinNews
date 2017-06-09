package vip.frendy.news.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.FragmentActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import vip.frendy.news.MainActivity
import vip.frendy.news.R
import vip.frendy.model.data.DelegatesExt
import vip.frendy.model.net.Request

class SplashActivity : FragmentActivity() {
    var uid: String by DelegatesExt.preference(this, "UID", "0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initUser()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity<MainActivity>()
            this.finish()
        }, 3000);
    }

    private fun initUser() = doAsync {
        val userID = Request(this@SplashActivity).init()
        uiThread {
            uid = userID.user_id
        }
    }
}
