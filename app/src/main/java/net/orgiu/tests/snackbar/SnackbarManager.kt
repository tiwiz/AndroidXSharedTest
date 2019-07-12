package net.orgiu.tests.snackbar

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import net.orgiu.tests.R

class SnackbarManager private constructor(
    private val constraintLayout: ConstraintLayout,
    private val recyclerView: RecyclerView
) {

    private val context = recyclerView.context
    private val adapter = recyclerView.adapter ?: error("Adapter should not be null")
    private val lManager = (
            recyclerView.layoutManager ?: error("LayoutManager should already be attached")
            ) as LinearLayoutManager

    fun showForItem(item: Int) {

        Snackbar.make(recyclerView, "Clicked item: $item", Snackbar.LENGTH_INDEFINITE)
            .let { snackbar ->
                snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onShown(transientBottomBar: Snackbar) {
                        super.onShown(transientBottomBar)
                        updateMarginTo(transientBottomBar.view.height, item)
                    }
                })
                    .setAction(android.R.string.ok) {
                        updateMarginTo(0, item)
                        snackbar.dismiss()
                    }
                    .show()
            }
    }

    private fun updateMarginTo(height: Int, item: Int) {
        val lastItem = adapter.itemCount - 1

        val set = ConstraintSet()
        set.clone(constraintLayout)
        set.setMargin(R.id.sampleList, ConstraintSet.BOTTOM, height)
        set.applyTo(constraintLayout)

        if (item == lastItem) {
            with(SmoothScroller(context)) {
                targetPosition = lastItem
                lManager.startSmoothScroll(this)
            }
        }
    }


    class Builder {

        private lateinit var constraintLayout: ConstraintLayout
        private lateinit var recyclerView: RecyclerView

        fun inside(constraintLayout: ConstraintLayout) = apply {
            this.constraintLayout = constraintLayout
        }

        fun with(recyclerView: RecyclerView) = apply {
            this.recyclerView = recyclerView
        }

        fun build() = SnackbarManager(constraintLayout, recyclerView)
    }
}