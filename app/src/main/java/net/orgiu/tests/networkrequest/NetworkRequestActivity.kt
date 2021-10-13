package net.orgiu.tests.networkrequest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import net.orgiu.tests.R

class NetworkRequestActivity : AppCompatActivity() {

    private val networkManager by lazy { Manager(this) }
    private val status by lazy {
        findViewById<TextView>(R.id.status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_request)

        networkManager.result.observe(this,
            Observer { result ->
                with(status) {
                    setText(result.messageResId)
                    setTextColor(
                        ContextCompat.getColor(this@NetworkRequestActivity, result.colorResId)
                    )
                }
            })
    }

    override fun onStart() {
        super.onStart()
        networkManager.registerCallback()
    }

    override fun onStop() {
        super.onStop()
        networkManager.unregisterCallback()
    }
}
