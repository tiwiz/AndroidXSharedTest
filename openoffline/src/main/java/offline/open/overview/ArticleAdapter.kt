package offline.open.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import offline.open.R
import offline.open.databinding.OverviewListItemBinding
import offline.open.models.ArticleOverview
import offline.open.models.Overview

class ArticleAdapter(
    context: Context,
    private val lifecycleProvider: LifecycleOwner
) : RecyclerView.Adapter<ArticleViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = arrayListOf<ArticleOverview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            DataBindingUtil.inflate<OverviewListItemBinding>(
                inflater,
                R.layout.overview_list_item,
                parent,
                false
            ).apply {
                lifecycleOwner = lifecycleProvider
            }
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

    fun updateItems(overview: Overview) {
        with(items) {
            clear()
            addAll(overview)
        }

        notifyDataSetChanged()
    }
}