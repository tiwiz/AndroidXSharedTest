package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.Item
import offline.open.models.OpenFeed
import offline.open.repository.TimestampParser.extractTimestamp
import org.jsoup.Jsoup

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
            description = Jsoup.parse(description).selectFirst("p").text(),
            content = content,
            categories = categories,
            timestamp = extractTimestamp(pubDate)
        )
}




