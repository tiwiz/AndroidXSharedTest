package net.orgiu.tests.intentsender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_CHOSEN_COMPONENT
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber

class ShareComponentReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val chosenApp = intent.extras?.get(EXTRA_CHOSEN_COMPONENT).toString()
        Timber.d("App chosen by the user: $chosenApp")
    }
}
