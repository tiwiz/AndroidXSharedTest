package net.orgiu.tests.networkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.orgiu.tests.R

class NetworkRequestActivity : AppCompatActivity() {

    private val networkManager by lazy { Manager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_request)
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
