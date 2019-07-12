package net.orgiu.tests.snackbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_snackbar.*
import net.orgiu.tests.R

class SnackbarActivity : AppCompatActivity() {

    private val sampleAdapter by lazy { SampleItemAdapter(this, this::onItemClick) }
    private val sampleLayoutManager by lazy { LinearLayoutManager(this) }

    private val snackbarManager by lazy {
        SnackbarManager.Builder()
            .with(sampleList)
            .inside(sampleRoot)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        with(sampleList) {
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


