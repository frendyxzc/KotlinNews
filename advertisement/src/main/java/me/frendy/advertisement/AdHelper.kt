package me.frendy.advertisement

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat
import android.view.View

import com.qq.e.ads.nativ.NativeAD
import com.qq.e.ads.nativ.NativeADDataRef

import me.frendy.advertisement.interfaces.IAd

/**
 * Created by iiMedia on 2017/5/18.
 */

class AdHelper(context: Context) : NativeAD.NativeAdListener {
    private val mContext: Context
    private val mEnable: Boolean = false

    companion object {
        private var adHelper: AdHelper? = null

        fun getInstance(context: Context): AdHelper {
            if (adHelper == null) {
                adHelper = AdHelper(context)
            }
            return adHelper as AdHelper
        }
    }

    fun onExposured(ref: NativeADDataRef, view: View) {
        ref.onExposured(view)
    }

    fun onClicked(ref: NativeADDataRef, view: View) {
        ref.onClicked(view)
    }

    init {
        mContext = context.applicationContext
    }

    /**
     * 初始化并加载广告
     */
    private var nativeAD: NativeAD? = null
    private var iAd: IAd? = null
    private val mAdList: ArrayList<NativeADDataRef> = ArrayList()
    private var mAdIndex: Int = 0

    fun loadAD(view: IAd? = null) {
        if(!mEnable) return

        if (nativeAD == null) {
            this.nativeAD = NativeAD(mContext, AdConstants.APPID, AdConstants.NativePosID, this)
        }
        val count = 10 // 一次拉取的广告条数：范围1-10
        nativeAD?.loadAD(count)
        iAd = view
    }

    fun getOneAd(): NativeADDataRef? {
        if(mAdList.isNotEmpty()) {
            if(mAdIndex >= mAdList.size - 1) loadAD()
            if(mAdIndex >= mAdList.size) mAdIndex %= mAdList.size
            return mAdList[mAdIndex ++]
        }
        return null
    }

    override fun onADLoaded(list: List<NativeADDataRef>) {
        if (list.isNotEmpty()) {
            mAdList.clear()
            mAdList.addAll(list)
            mAdIndex = 0
            iAd?.onADLoaded(list)
        }
    }

    override fun onNoAD(i: Int) {  }

    override fun onADStatusChanged(nativeADDataRef: NativeADDataRef) {  }

    override fun onADError(nativeADDataRef: NativeADDataRef, i: Int) {  }


    /**
     * 权限相关
     */
    private val REQUEST_CODE_PERMISSIONS = 0x11
    private val permissions = arrayOf(
            "android.permission.READ_PHONE_STATE",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.ACCESS_NETWORK_STATE")

    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_PERMISSIONS)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == REQUEST_CODE_PERMISSIONS) {
            loadAD()
        }
    }
}
