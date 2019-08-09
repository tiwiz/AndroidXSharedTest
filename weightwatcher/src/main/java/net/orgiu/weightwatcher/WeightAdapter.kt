package net.orgiu.weightwatcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import net.orgiu.weightwatcher.databinding.WeightItemBinding

class WeightHolder(private val binding: WeightItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(uiModule: UiModule) {
        with(binding) {
            uiModel = uiModule
            executePendingBindings()
        }
    }
}

class WeightAdapter(host: AppCompatActivity) : RecyclerView.Adapter<WeightHolder>() {

    private val weights = arrayListOf<UiModule>()
    private val inflater = LayoutInflater.from(host)
    private val host: LifecycleOwner = host

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightHolder =
        WeightHolder(
            DataBindingUtil.inflate<WeightItemBinding>(
                inflater,
                R.layout.weight_item,
                parent,
                false
            ).apply {
                lifecycleOwner = host
            }
        )

    override fun getItemCount() = weights.size

    override fun onBindViewHolder(holder: WeightHolder, position: Int) {
        holder.bindTo(weights[position])
    }

    fun updateWeights(newWeights: List<UiModule>) {
        with(weights) {
            clear()
            addAll(newWeights)
        }

        notifyDataSetChanged()
    }
}