package offline.open.overview

import androidx.recyclerview.widget.RecyclerView
import offline.open.common.DetailHandler
import offline.open.databinding.OverviewListItemBinding
import offline.open.models.ArticleOverview

class ArticleViewHolder(private val binding: OverviewListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(
        articleOverview: ArticleOverview,
        detailHandler: DetailHandler
    ) {
        with(binding) {
            article = articleOverview
            handler = detailHandler
            executePendingBindings()
        }
    }
}