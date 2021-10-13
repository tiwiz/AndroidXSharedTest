package net.orgiu.tests

import android.app.Application
import net.orgiu.tests.nightmode.NightModeActivityCallbacks
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(
            NightModeActivityCallbacks(this)
        )

        plant(DebugTree())
    }
}