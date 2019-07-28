package offline.open.models

data class OpenFeed (
    val status: String,
    val feed: Feed,
    val items: List<Item>
)

data class Feed (
    val url: String,
    val title: String,
    val link: String,
    val author: String,
    val description: String,
    val image: String
)

data class Item (
    val title: String,
    val pubDate: String,
    val link: String,
    val guid: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val content: String,
    val categories: List<String>
)

