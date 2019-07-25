package net.orgiu.tests.textscaling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityTextScalingDataBindingOnlyBinding

class TextScalingDataBindingOnlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTextScalingDataBindingOnlyBinding>(this,R.layout.activity_text_scaling_data_binding_only).also {
            it.textScale = TextScaleInt()
            it.lifecycleOwner = this
        }
    }
}
