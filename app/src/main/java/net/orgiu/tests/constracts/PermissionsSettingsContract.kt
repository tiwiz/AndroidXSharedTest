package net.orgiu.tests.constracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract

class PermissionsSettingsContract : ActivityResultContract<Nothing, Boolean>() {

    override fun createIntent(context: Context, input: Nothing?): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean = true
}