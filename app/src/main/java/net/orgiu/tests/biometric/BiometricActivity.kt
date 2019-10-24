package net.orgiu.tests.biometric

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_biometric.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.orgiu.tests.R

class BiometricActivity : AppCompatActivity() {

    private val contentView by lazy {
        findViewById<View>(android.R.id.content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        authenticate.setOnClickListener {
            runAuthentication()
        }
    }

    private fun runAuthentication() {
        lifecycleScope.launch {
            try {
                authenticate(buildPromptInfo())
                Snackbar.make(contentView, "Authenticated!", Snackbar.LENGTH_SHORT).show()
            } catch (e: BiometricException) {
                Snackbar.make(contentView, e.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
