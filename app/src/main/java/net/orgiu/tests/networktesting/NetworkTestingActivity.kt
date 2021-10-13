package net.orgiu.tests.networktesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.orgiu.tests.databinding.ActivityNetworkTestingBinding

class NetworkTestingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNetworkTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}