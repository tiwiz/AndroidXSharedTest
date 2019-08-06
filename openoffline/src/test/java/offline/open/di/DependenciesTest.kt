package offline.open.di

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import offline.open.models.LceView
import offline.open.models.Overview
import offline.open.models.OverviewDispatcher
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DependenciesTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val mockOverviewView: LceView<Overview> = mock()

    @Test
    fun verify_dependency_injection() {
        koinApplication {
            androidContext(context)
            modules(fetchModules())
        }.checkModules {
            create<OverviewDispatcher> { parametersOf(mockOverviewView) }
        }
    }
}