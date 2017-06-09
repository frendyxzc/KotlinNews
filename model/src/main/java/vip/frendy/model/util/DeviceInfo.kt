package vip.frendy.model.util

import android.content.Context
import android.provider.Settings

/**
 * Created by iiMedia on 2017/6/2.
 */

object DeviceInfo {

    fun getAndroidID(context: Context): String {
        return Settings.System.getString(context.contentResolver, Settings.System.ANDROID_ID)
    }
}