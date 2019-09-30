package com.thesaurus.branchview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils.TruncateAt.START
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView

internal class DebugBranchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val textView by lazy { TextView(context) }
    private val layoutParams by lazy { createLayoutParams() }
    private var configuration: Configuration = Configuration()

    init {
        textView.apply {
            update()
            setPadding(DPsFrom(8), DPsFrom(8), DPsFrom(8), DPsFrom(8))
            setSingleLine()
            ellipsize = START
        }

        addView(textView, layoutParams)

        setBackgroundColor(Color.TRANSPARENT)

        isClickable = false
    }

    private fun createLayoutParams(): LayoutParams =
        LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            .apply {
                setMargins(DPsFrom(4), DPsFrom(4), DPsFrom(4), DPsFrom(4))
                gravity = Gravity.BOTTOM or Gravity.END
            }

    fun updateConfiguration(configuration: Configuration) {
        this.configuration = configuration
        textView.update()
    }

    fun updateMessage(message: String) {
        textView.text = message
    }

    private fun TextView.update() {
        setTextColor(configuration.textColor)
        setBackgroundColor(configuration.backgroundColor)

        typeface = configuration.typeface
        textSize = configuration.textSize
    }
}




