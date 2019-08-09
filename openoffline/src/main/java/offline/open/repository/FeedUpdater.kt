package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.OpenFeed
import offline.open.network.FeedApi

class FeedUpdater(private val api: FeedApi,
                  private val dao: ArticleDao,
                  private val parser: FeedParser) {

    suspend fun updateContent() {
        api.fetch().parse().writeOnDb()
    }

    private fun OpenFeed.parse() =
        parser.parse(this)

    private suspend fun List<ArticleData>.writeOnDb() =
        dao.insertArticles(this)
}