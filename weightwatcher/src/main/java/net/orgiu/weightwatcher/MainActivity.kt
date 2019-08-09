package net.orgiu.weightwatcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.UnstableDefault

@UnstableDefault
class MainActivity : AppCompatActivity() {

    private val weightAdapter by lazy { WeightAdapter(this) }
    private val weightRepository by lazy { WeightRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(weightList) {
            adapter = weightAdapter
            layoutManager = LinearLayoutManager(context)
        }

        addWeightButton.setOnClickListener {
            startActivity(Intent(this, WeightRecordActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        weightAdapter.updateWeights(
            weightRepository.fetchUiModels()
        )
    }
}
