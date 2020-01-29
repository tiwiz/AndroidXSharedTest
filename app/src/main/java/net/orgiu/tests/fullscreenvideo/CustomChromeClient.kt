package net.orgiu.tests.fullscreenvideo

import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView

class CustomChromeClient(private val container: ViewGroup,
                         private val webView: WebView) : WebChromeClient() {

    private var callback: CustomViewCallback? = null

    override fun onShowCustomView(
        view: View?,
        requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
        onShowCustomView(view, callback)
    }

    override fun onShowCustomView(view: View?, viewCallback: CustomViewCallback?) {
        container.addView(view)
        callback = viewCallback

        webView.visibility = View.GONE
        container.visibility = View.VISIBLE
    }

    override fun onHideCustomView() {
        webView.visibility = View.VISIBLE
        container.visibility = View.GONE

        container.removeAllViews()
        callback?.onCustomViewHidden()
    }
}