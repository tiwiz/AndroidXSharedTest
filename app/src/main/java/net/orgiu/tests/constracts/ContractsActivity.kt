package net.orgiu.tests.constracts

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityContractsBinding

class ContractsActivity : AppCompatActivity() {

    private val binding: ActivityContractsBinding by lazy {
        ActivityContractsBinding.inflate(layoutInflater)
    }

    private val wantedPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val requestPermissionsContracts =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (it.key.contains("FINE")) {
                    binding.imgFinePermission.setImageResource(it.value.toImageRes())
                } else {
                    binding.imgCoarsePermission.setImageResource(it.value.toImageRes())
                }
            }
        }

    private val takePictureContract =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            binding.resultImage.setImageBitmap(bitmap)
        }

    private val openAppSettingsContract =
        registerForActivityResult(PermissionsSettingsContract()) {
            /* DO NOTHING */
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRequestGPSPermission.setOnClickListener {
            requestPermissionsContracts.launch(wantedPermissions)
        }

        binding.btnRequestPicture.setOnClickListener {
            takePictureContract.launch(null)
        }

        binding.btnOpenSettingsScreen.setOnClickListener {
            openAppSettingsContract.launch(null)
        }

        binding.btnExample.setOnClickListener {
            startActivity(Intent(this, PermissionExampleActivity::class.java))
        }
    }

    @DrawableRes
    private fun Boolean.toImageRes() =
        if (this) {
            R.drawable.ic_lock_open
        } else {
            R.drawable.ic_lock_closed
        }
}
