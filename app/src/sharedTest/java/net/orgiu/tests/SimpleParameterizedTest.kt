package net.orgiu.tests


import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SimpleParameterizedTest(val theInt: Int, val theString: String) {

    val context = ApplicationProvider.getApplicationContext<Application>()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(arrayOf(0, "0"), arrayOf(1, "1"))
        }
    }

    @Test
    fun simple_test_case() {
        assert(theInt == theString.toInt())
    }
}