package offline.open.repository

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.KoinComponent
import org.koin.core.inject

class UpdateWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    override suspend fun doWork(): Result {
        val feedUpdater: FeedUpdater by inject()

        return try {
            feedUpdater.updateContent()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}