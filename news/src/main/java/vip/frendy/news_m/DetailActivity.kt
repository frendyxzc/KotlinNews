package vip.frendy.news_m

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_detail.*
import vip.frendy.model.router.Router

class DetailActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra(Router.NEWS_URL)
        webView.loadUrl(url)
    }
}
