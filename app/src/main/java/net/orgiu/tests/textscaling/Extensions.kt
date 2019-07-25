package net.orgiu.tests.textscaling

import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData

fun SeekBar.onSeekChangeListener(onProgress: SeekBar.(Int, Boolean) -> Unit) {
    setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            onProgress(seekBar, progress, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    })
}

class TextScale(initialValue: Float = 1f) : MutableLiveData<Float>() {

    init {
        postValue(initialValue)
    }
}

class TextScaleInt(initialValue: Int = 1) : MutableLiveData<Int>() {

    init {
        postValue(initialValue)
    }
}