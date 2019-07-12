package net.orgiu.tests.snackbar

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleViewHolder(
    itemView: View,
    private val callback: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val label by lazy { itemView.findViewById<TextView>(android.R.id.text1) }

    @SuppressLint("SetTextI18n")
    fun bindTo(item: Int) {
        label.text = "Item $item"

        itemView.setOnClickListener {
            callback(item)
        }
    }
}