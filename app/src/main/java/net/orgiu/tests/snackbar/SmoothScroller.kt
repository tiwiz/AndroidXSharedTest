package net.orgiu.tests.snackbar

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

class SmoothScroller(context: Context, private val msPerInch: Float = 400f) :
    LinearSmoothScroller(context) {

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return msPerInch / displayMetrics.densityDpi
    }
}