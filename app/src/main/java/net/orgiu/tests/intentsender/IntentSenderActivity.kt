package net.orgiu.tests.intentsender

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityIntentSenderBinding

class IntentSenderActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityIntentSenderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnShare.setOnClickListener {
            startActivity(Intent.createChooser(shareIntent(), "Share!", intentSender()))
        }
    }

    private fun shareIntent() = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Sample text to share!")
        type = "text/plain"
    }

    private fun intentSender() : IntentSender {
        val receiverIntent = Intent(this, ShareComponentReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return pendingIntent.intentSender
    }
}
