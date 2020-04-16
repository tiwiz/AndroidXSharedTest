package net.orgiu.tests.lce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityLceBinding
import net.orgiu.tests.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class LceActivity : AppCompatActivity() {

    private var binding: ActivityLceBinding by Delegates.notNull()
    private val vm: LceViewModel by viewModels()

    private var delay: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.complete.setOnClickListener {
            runAction(true)
        }

        binding.error.setOnClickListener {
            runAction(false)
        }

        binding.delaySeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                delay = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        })

        vm.result.observe(this, Observer {
            val text = when (it) {
                is LCE.Loading -> "loading"
                is LCE.Complete -> "complete"
                is LCE.Error -> "error"
            }

            binding.output.text = text
        })
    }

    private fun runAction(shouldSucceed: Boolean) {
        vm.runFunction(shouldSucceed, delay)
    }
}