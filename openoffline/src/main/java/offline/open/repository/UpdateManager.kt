package offline.open.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class UpdateManager {

    private val constraints = Constraints.Builder()
        .setRequiresDeviceIdle(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val updateJob = PeriodicWorkRequestBuilder<UpdateWorker>(2, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()

    fun scheduleUpdateJobs(context: Context) {
        WorkManager.getInstance(context).enqueue(updateJob)
    }
}