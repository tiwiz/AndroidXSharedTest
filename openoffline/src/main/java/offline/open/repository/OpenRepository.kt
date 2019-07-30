package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.ArticleDetails
import offline.open.models.ArticleOverview
import offline.open.models.OpenFeed
import offline.open.network.FeedApi

class OpenRepository(
    private val api: FeedApi,
    private val dao: ArticleDao,
    private val parser: FeedParser
) : Repository {

    override suspend fun updateContent() {
        api.fetch().parse().writeOnDb()
    }

    private fun OpenFeed.parse() =
        parser.parse(this)

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