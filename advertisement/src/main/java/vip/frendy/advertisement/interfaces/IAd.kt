package vip.frendy.advertisement.interfaces

import com.qq.e.ads.nativ.NativeADDataRef

/**
 * Created by iiMedia on 2017/5/18.
 */

interface IAd {
    fun onADLoaded(list: List<NativeADDataRef>)
}
