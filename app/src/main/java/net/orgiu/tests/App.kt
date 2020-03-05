package net.orgiu.tests

import android.app.Application
import net.orgiu.tests.nightmode.NightModeActivityCallbacks

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(
            NightModeActivityCallbacks(this)
        )
    }
}