package net.orgiu.tests.constracts

import android.Manifest
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import net.orgiu.tests.databinding.ActivityContractsBinding
import net.orgiu.tests.databinding.ActivityPermissionExampleBinding

class PermissionExampleActivity : AppCompatActivity() {

    private val binding: ActivityPermissionExampleBinding by lazy {
        ActivityPermissionExampleBinding.inflate(layoutInflater)
    }

    private val permission = Manifest.permission.ACCESS_FINE_LOCATION

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                onMoreInfoNeeded()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        when {
            ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED -> onPermissionGranted()
            shouldShowRequestPermissionRationale(permission) -> onMoreInfoNeeded()
            else -> requestPermissionLauncher.launch(permission)
        }
    }

    private fun onPermissionGranted() {
        binding.permissionResult.text = "PERMISSION HAS BEEN GRANTED"
    }

    private fun onMoreInfoNeeded() {
        binding.permissionResult.text = "PERMISSION HAS NOT BEEN GRANTED"
    }
}
