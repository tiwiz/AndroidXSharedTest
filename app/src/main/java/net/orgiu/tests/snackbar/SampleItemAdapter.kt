package net.orgiu.tests.snackbar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SampleItemAdapter(
    context: Context,
    private val callback: (Int) -> Unit,
    private val size: Int = 15
) : RecyclerView.Adapter<SampleViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder =
        SampleViewHolder(
            inflater.inflate(android.R.layout.simple_list_item_1, parent, false),
            callback
        )

    override fun getItemCount() = size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bindTo(position)
    }
}