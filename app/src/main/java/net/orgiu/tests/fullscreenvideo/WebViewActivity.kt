package net.orgiu.tests.fullscreenvideo

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import net.orgiu.tests.R


class WebViewActivity : AppCompatActivity() {

    private val chromeClient by lazy { HackyChromeClient(this) }

    private val webView by lazy {
        findViewById<WebView>(R.id.webView)
    }

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
            webChromeClient = chromeClient
//            webChromeClient = CustomChromeClient(fullscreenView, this)
            loadUrl("https://www.youtube.com/")
        }
    }
}
