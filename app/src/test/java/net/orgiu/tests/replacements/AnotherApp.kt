package net.orgiu.tests.replacements

import android.app.Application
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

class AnotherApp : Application(), TestLifecycleApplication {

    override fun beforeTest(method: Method?) {

    }

    override fun prepareTest(test: Any?) {

    }

    override fun afterTest(method: Method?) {

    }
}