package net.orgiu.tests.fullscreenvideo

import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }
}