package offline.open.repository

import offline.open.models.ArticleData
import offline.open.models.Feed
import offline.open.models.Item
import offline.open.models.OpenFeed
import org.threeten.bp.Instant

object SampleData {

    val feedArticle1 = Item(
        title = "Titolo 1",
        pubDate = "2019-07-30 12:08:08",
        link = "http://open.online/titolo_1",
        guid = "https://www.open.online/?p=1",
        author = "autore autore",
        thumbnail = "http://image.jpg",
        description = "<p>Lorem Ipsum</p><p>L'articolo...</p><p>...</p>",
        content = "Lorem Ipsum Dolor Sit Amet",
        categories = listOf("categoria1", "categoria2")
    )

    val feed = Feed(
        url = "https://www.open.online/feed/",
        title = "Open",
        link = "https://www.open.online/",
        author = "",
        description = "Il giornale online fondato da Enrico Mentana",
        image = ""
    )

    val openFeed = OpenFeed(
        status = "ok",
        feed = feed,
        items = listOf(feedArticle1)
    )

    val dbArticle1 = ArticleData(
        articleId = "https://www.open.online/?p=1",
        title = "Titolo 1",
        link = "http://open.online/titolo_1",
        author = "autore autore",
        thumbnail = "http://image.jpg",
        description = "Lorem Ipsum",
        content = "Lorem Ipsum Dolor Sit Amet",
        categories = listOf("categoria1", "categoria2"),
        timestamp = Instant.ofEpochSecond(1564481288)
    )
}