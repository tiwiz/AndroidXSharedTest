package net.orgiu.tests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_snackbar.*
import net.orgiu.tests.snackbar.SampleItemAdapter
import net.orgiu.tests.snackbar.SmoothScroller

class SnackbarActivity : AppCompatActivity() {

    private val sampleAdapter by lazy { SampleItemAdapter(this, this::onItemClick) }
    private val linearSmoothScroller by lazy { SmoothScroller(this) }
    private val sampleLayoutManager by lazy { LinearLayoutManager(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        with(sampleList) {
            adapter = sampleAdapter
            layoutManager = sampleLayoutManager
        }

    }

    private fun onItemClick(item: Int) {

        Snackbar.make(sampleList, "Clicked item: $item", Snackbar.LENGTH_INDEFINITE)
            .let { snackbar ->
                snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onShown(transientBottomBar: Snackbar?) {
                        super.onShown(transientBottomBar)
                        setPaddingTo(transientBottomBar?.view?.height ?: 1000)
                    }

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        setPaddingTo(0)
                    }
                })
                    .setAction(android.R.string.ok) { snackbar.dismiss() }
                    .show()
            }


    }

    private fun setPaddingTo(height: Int) {
        val lastItem = sampleAdapter.itemCount - 1
        val lastVisibleItem = sampleLayoutManager.findLastCompletelyVisibleItemPosition()

        if (lastItem == lastVisibleItem) {
            val set = ConstraintSet()
            set.clone(sampleRoot)
            set.setMargin(R.id.sampleList, 4, height)
            set.applyTo(sampleRoot)

            linearSmoothScroller.targetPosition = lastItem
            sampleLayoutManager.startSmoothScroll(linearSmoothScroller)
        }
    }
}


