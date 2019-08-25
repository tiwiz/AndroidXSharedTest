package offline.open.repository

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import offline.open.di.fetchModules
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

@RunWith(AndroidJUnit4::class)
class UpdateWorkerTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()
    private val feedUpdater: FeedUpdater by inject()

    @Before
    fun setup() {
        startKoin {
            androidContext(context)
            modules(fetchModules())
        }
        declareMock<FeedUpdater>()
    }

    @Test
    fun when_update_goes_wrong_it_should_return_a_failure() {
        val worker = TestListenableWorkerBuilder<UpdateWorker>(context).build()

        runBlocking {
            given(feedUpdater.updateContent()).will { throw RuntimeException() }
            assertThat(worker.doWork()).isEqualTo(Result.retry())
        }
    }

    @Test
    fun when_update_goes_smoothly_it_should_return_success() {
        val worker = TestListenableWorkerBuilder<UpdateWorker>(context).build()

        runBlocking {
            given(feedUpdater.updateContent()).will { /* do nothing */ }
            assertThat(worker.doWork()).isEqualTo(Result.success())
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}