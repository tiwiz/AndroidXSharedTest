package net.orgiu.tests.main

import android.content.Intent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import net.orgiu.tests.R
import net.orgiu.tests.biometric.BiometricActivity
import net.orgiu.tests.fragmentslifecycle.FragmentListenerActivity
import net.orgiu.tests.fullscreenvideo.WebViewActivity
import net.orgiu.tests.networkrequest.NetworkRequestActivity
import net.orgiu.tests.nightmode.NightModeActivity
import net.orgiu.tests.snackbar.SnackbarActivity
import net.orgiu.tests.textscaling.TextScalingActivity
import net.orgiu.tests.textscaling.TextScalingDataBindingOnlyActivity

data class Functionality<T : AppCompatActivity>(
    @StringRes val buttonTitle: Int,
    val activityClass: Class<T>
)

val functions = arrayOf(
    Functionality(R.string.snackbar_test, SnackbarActivity::class.java),
    Functionality(R.string.mixed_text_scaling, TextScalingActivity::class.java),
    Functionality(R.string.databinding_only_text_scaling, TextScalingDataBindingOnlyActivity::class.java),
    Functionality(R.string.biometric_prompt, BiometricActivity::class.java),
    Functionality(R.string.network_request_builder, NetworkRequestActivity::class.java),
    Functionality(R.string.fullscreen_webview_video, WebViewActivity::class.java),
    Functionality(R.string.night_mode, NightModeActivity::class.java),
    Functionality(R.string.fragment_lifecycle, FragmentListenerActivity::class.java)
)

fun AppCompatActivity.launchBy(functionality: Functionality<*>) {
    startActivity(Intent(this, functionality.activityClass))
}