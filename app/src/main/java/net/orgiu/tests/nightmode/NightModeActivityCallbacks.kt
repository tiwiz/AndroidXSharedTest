package net.orgiu.tests.nightmode

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate

class NightModeActivityCallbacks(context: Context) : Application.ActivityLifecycleCallbacks {

    private val nightModeStorage by lazy {
        NightModeStorage(context)
    }

    override fun onActivityPaused(p0: Activity) = Unit

    override fun onActivityResumed(p0: Activity)  = Unit

    override fun onActivityStarted(p0: Activity)  = Unit

    override fun onActivityDestroyed(p0: Activity)  = Unit

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle)  = Unit

    override fun onActivityStopped(p0: Activity)  = Unit

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        if(activity is NightModeActivity) {
            val nightModeType = nightModeStorage.fetchNightMode()
            applySystemModeTheme(nightModeType)
        }
    }

    private fun applySystemModeTheme(nightModeType: NightModeType) {
        AppCompatDelegate.setDefaultNightMode(nightModeType.flag)
    }
}