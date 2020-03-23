package net.orgiu.tests

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.orgiu.tests.biometric.BiometricActivity
import net.orgiu.tests.fragmentslifecycle.FragmentListenerActivity
import net.orgiu.tests.fullscreenvideo.WebViewActivity
import net.orgiu.tests.networkrequest.NetworkRequestActivity
import net.orgiu.tests.nightmode.NightModeActivity
import net.orgiu.tests.snackbar.SnackbarActivity
import net.orgiu.tests.textscaling.TextScalingActivity
import net.orgiu.tests.textscaling.TextScalingDataBindingOnlyActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        snackbar.setOnClickListener {
            startActivity(Intent(this, SnackbarActivity::class.java))
        }

        mixedTextScaling.setOnClickListener {
            startActivity(Intent(this, TextScalingActivity::class.java))
        }

        dataBindingOnlyTextScaling.setOnClickListener {
            startActivity(Intent(this, TextScalingDataBindingOnlyActivity::class.java))
        }

        biometricPrompt.setOnClickListener {
            startActivity(Intent(this, BiometricActivity::class.java))
        }

        networkRequestBuilder.setOnClickListener {
            startActivity(Intent(this, NetworkRequestActivity::class.java))
        }

        fullscreenWebViewVideo.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        nightMode.setOnClickListener {
            startActivity(Intent(this, NightModeActivity::class.java))
        }

        fragmentLifecycle.setOnClickListener {
            startActivity(Intent(this, FragmentListenerActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
