package vip.frendy.news.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import vip.frendy.news.R

class DetailActivity : FragmentActivity() {

    companion object {
        val URL: String = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra(URL)
        webView.loadUrl(url)
    }
}
