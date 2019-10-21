package net.orgiu.tests.replacements

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isTrue
import net.orgiu.tests.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = AnotherApp::class)
class ScenarioTest {

    private val scenario = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun verify_app_type() {
        scenario.onActivity {
            val isExpectedType = it.application is AnotherApp

            assertThat(isExpectedType).isTrue()
        }
    }
}