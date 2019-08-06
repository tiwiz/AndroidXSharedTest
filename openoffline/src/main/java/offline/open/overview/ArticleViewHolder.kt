package offline.open.overview

import androidx.recyclerview.widget.RecyclerView
import offline.open.databinding.OverviewListItemBinding
import offline.open.models.ArticleOverview

class ArticleViewHolder(private val binding: OverviewListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(articleOverview: ArticleOverview) {
        binding.article = articleOverview
//        binding.executePendingBindings()
    }
}