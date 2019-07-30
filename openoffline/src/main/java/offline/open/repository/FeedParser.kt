package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.Item
import offline.open.models.OpenFeed
import offline.open.repository.TimestampParser.extractTimestamp

class FeedParser {

    fun parse(feed: OpenFeed): List<ArticleData> =
        feed.items.map { it.toArticleData() }

    private fun Item.toArticleData(): ArticleData =
        ArticleData(
            articleId = guid,
            title = title,
            link = link,
            author = author,
            thumbnail = thumbnail,
            description = description,
            content = content,
            categories = categories,
            timestamp = extractTimestamp(pubDate)
        )
}




