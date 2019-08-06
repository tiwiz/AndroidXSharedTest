package offline.open

import android.app.Application
import offline.open.di.injectDependencies

class OpenApp : Application() {

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }
}