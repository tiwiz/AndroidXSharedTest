package offline.open.repository

import offline.open.models.ArticleDetails
import offline.open.models.ArticleOverview

interface Repository {

    suspend fun updateContent()

    suspend fun getOverview(): List<ArticleOverview>

    suspend fun getArticleDetails(articleId: String): ArticleDetails
}