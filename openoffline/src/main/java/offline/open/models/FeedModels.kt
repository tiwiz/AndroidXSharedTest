package offline.open.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenFeed (
    val status: String,
    val feed: Feed,
    val items: List<Item>
)

@Serializable
data class Feed (
    val url: String,
    val title: String,
    val link: String,
    val author: String,
    val description: String,
    val image: String
)

@Serializable
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

