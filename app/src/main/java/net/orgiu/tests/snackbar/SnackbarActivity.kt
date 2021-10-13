package net.orgiu.tests.snackbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivitySnackbarBinding

class SnackbarActivity : AppCompatActivity() {

    private val sampleAdapter by lazy { SampleItemAdapter(this, this::onItemClick) }
    private val sampleLayoutManager by lazy { LinearLayoutManager(this) }

    private lateinit var binding : ActivitySnackbarBinding

    private val snackbarManager by lazy {
        SnackbarManager.Builder()
            .with(binding.sampleList)
            .inside(binding.sampleRoot)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.sampleList) {
            adapter = sampleAdapter
            layoutManager = sampleLayoutManager
        }

    }

    private fun onItemClick(item: Int) {
        snackbarManager.showForItem(item)

//        val snackbar = Snackbar.make(sampleList, "Clicked item: $item", Snackbar.LENGTH_INDEFINITE)
//        snackbar.setAction(android.R.string.ok) { snackbar.dismiss() }
//            .show()
    }
}


