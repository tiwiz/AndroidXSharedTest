package offline.open.di

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DependenciesTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun verify_supported_languages_injection() {
        koinApplication {
            androidContext(context)
            modules(fetchModules()) }.checkModules()
    }
}