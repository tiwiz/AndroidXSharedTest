package net.orgiu.tests.constracts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.orgiu.tests.databinding.ActivityCustomPermissionBinding

class CustomPermissionActivity : AppCompatActivity() {

    private val binding: ActivityCustomPermissionBinding by lazy {
        ActivityCustomPermissionBinding.inflate(layoutInflater)
    }

    private val openAppSettingsContract =
        registerForActivityResult(SystemSettingsContract()) {
            Toast.makeText(this, "Came back!", Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnOpenSettings.setOnClickListener {
            openAppSettingsContract.launch(null)
        }
    }
}
