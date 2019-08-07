package offline.open

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import offline.open.common.initTools
import offline.open.di.injectDependencies
import offline.open.repository.UpdateManager

class OpenApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        injectDependencies()
        initTools()

        UpdateManager().scheduleUpdateJobs(this)
    }
}