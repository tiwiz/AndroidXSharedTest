package net.orgiu.tests.fullscreenvideo

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout

class HackyChromeClient(private val activity: Activity) : WebChromeClient() {

    private var customView: View? = null
    private var viewCallback: CustomViewCallback? = null
    private var originalOrientation = 0
    private var originalSystemUiVisibility = 0

    override fun getDefaultVideoPoster(): Bitmap? {
        return BitmapFactory.decodeResource(activity.resources, 2130837573)
    }

    override fun onHideCustomView() {
        (activity.window.decorView as FrameLayout).removeView(customView)
        customView = null
        activity.window.decorView.systemUiVisibility = originalSystemUiVisibility
        activity.requestedOrientation = originalOrientation
        viewCallback!!.onCustomViewHidden()
        viewCallback = null
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (customView != null) {
            onHideCustomView()
            return
        }
        customView = view
        originalSystemUiVisibility = activity.window.decorView.systemUiVisibility
        originalOrientation = activity.requestedOrientation
        viewCallback = callback
        (activity.window.decorView as FrameLayout).addView(
            customView,
            FrameLayout.LayoutParams(-1, -1)
        )
        activity.window.decorView.systemUiVisibility = 3846
    }
}