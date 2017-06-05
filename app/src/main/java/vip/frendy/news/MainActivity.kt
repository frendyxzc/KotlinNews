package vip.frendy.news

import android.os.Bundle
import vip.frendy.news.activity.BaseFragmentActivity
import vip.frendy.news.fragment.FragmentNewsList

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDefaultFragment(FragmentNewsList.instance)
    }
}

