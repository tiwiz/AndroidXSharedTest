package com.thesaurus.branchview

import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.WindowManager

@Suppress("FunctionName")
internal fun View.DPsFrom(value: Int): Int =
    (resources.displayMetrics.density * value).toInt()

internal val Context.screenWidth: Int
    get() = dimension { x }

internal val Context.screenHeight: Int
    get() = dimension { y }

private fun Context.dimension(retF: Point.() -> Int): Int {
    (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.let { display ->
        val sizes = Point()
        display.getSize(sizes)
        return retF(sizes)
    }
}