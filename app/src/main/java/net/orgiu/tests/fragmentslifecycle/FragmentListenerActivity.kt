package net.orgiu.tests.fragmentslifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.orgiu.tests.R

class FragmentListenerActivity : AppCompatActivity() {

    private val callbacks = LifecycleListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_listener)

        supportFragmentManager.registerFragmentLifecycleCallbacks(callbacks, true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, SimpleFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        supportFragmentManager.unregisterFragmentLifecycleCallbacks(callbacks)
    }
}