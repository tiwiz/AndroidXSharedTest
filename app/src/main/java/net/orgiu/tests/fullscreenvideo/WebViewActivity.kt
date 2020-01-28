package net.orgiu.tests.fullscreenvideo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import net.orgiu.tests.R


class WebViewActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView.settings.run {
            javaScriptEnabled = true
            allowFileAccess = true
            setAppCacheEnabled(true)
        }

        with(webView) {
            webViewClient = CustomWebViewClient()
//            webChromeClient = HackyChromeClient(this@WebViewActivity)
            webChromeClient = CustomChromeClient(fullscreenView, this)
            loadUrl("https://www.youtube.com/")
        }
    }
}
