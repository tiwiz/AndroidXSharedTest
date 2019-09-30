package com.thesaurus.branchview

import android.graphics.Color
import android.graphics.Typeface
import androidx.annotation.ColorInt

data class Configuration(
    @ColorInt val textColor: Int = Color.WHITE,
    @ColorInt val backgroundColor : Int = Color.BLACK,
    val typeface: Typeface = Typeface.MONOSPACE,
    val textSize: Float = 14f,
    val shouldShow: Boolean = true
)