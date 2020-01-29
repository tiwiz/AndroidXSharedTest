package net.orgiu.tests.fullscreenvideo

import android.app.Activity
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import android.widget.FrameLayout.*

class HackyChromeClient(private val activity: Activity) : WebChromeClient() {

    private var customView: View? = null
    private var viewCallback: CustomViewCallback? = null
    private var originalOrientation = 0
    private var originalSystemUiVisibility = 0

    private var systemUi: Int
        get() = activity.window.decorView.systemUiVisibility
        set(value) {
            activity.window.decorView.systemUiVisibility = value
        }

    private val root: FrameLayout
        get() = activity.window.decorView as FrameLayout

    private var requestedOrientation
        get() = activity.requestedOrientation
        set(value) {
            activity.requestedOrientation = value
        }

    override fun onHideCustomView() {
        root.removeView(customView)
        systemUi = originalSystemUiVisibility
        requestedOrientation = originalOrientation

        viewCallback?.onCustomViewHidden()
        customView = null
        viewCallback = null
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (customView != null) {
            onHideCustomView()
        } else {
            showFullscreenView(view, callback)
        }
    }

    private fun showFullscreenView(
        view: View?,
        callback: CustomViewCallback?
    ) {
        customView = view

        originalSystemUiVisibility = systemUi
        originalOrientation = requestedOrientation
        viewCallback = callback
        root.addView(customView, LayoutParams(-1, -1))
        systemUi = REQUESTED_FLAGS
    }

    companion object {

        const val REQUESTED_FLAGS = SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_FULLSCREEN or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                SYSTEM_UI_FLAG_IMMERSIVE

    }
}