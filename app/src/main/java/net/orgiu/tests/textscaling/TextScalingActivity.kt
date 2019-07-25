package net.orgiu.tests.textscaling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import kotlinx.android.synthetic.main.activity_text_scaling.*
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityTextScalingBinding

class TextScalingActivity : AppCompatActivity() {

    private val textScaleEvent = TextScale()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO ActivityTextScalingBinding Vs Impl
        setContentView<ActivityTextScalingBinding>(this, R.layout.activity_text_scaling).also {
            it.textScale = textScaleEvent
            it.lifecycleOwner = this
        }

        textScaleSeekBar.onSeekChangeListener { progress, fromUser ->
            if (fromUser) {
                textScaleEvent.postValue(progress.toFloat())
            }
        }
    }
}
