package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.ArticleDetails
import offline.open.models.ArticleOverview

class OpenRepository(
    private val dao: ArticleDao,
    private val feedUpdater: FeedUpdater
) : Repository {

    override suspend fun updateContent() {
        feedUpdater.updateContent()
    }

    private suspend fun List<ArticleData>.writeOnDb() =
        dao.insertArticles(this)

    override suspend fun getOverview(): List<ArticleOverview> {
        val actual = dao.fetchOverview()
        return if (actual.isEmpty()) {
            updateContent()
            dao.fetchOverview()
        } else {
            actual
        }
    }

    override suspend fun getArticleDetails(articleId: String): ArticleDetails =
        dao.fetchArticle(articleId)

}