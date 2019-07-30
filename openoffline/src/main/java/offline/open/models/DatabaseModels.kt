package offline.open.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Instant

@Entity(tableName = "articles")
data class ArticleData(
    @ColumnInfo(name = "article_id") @PrimaryKey val articleId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "categories") val categories: List<String>,
    @ColumnInfo(name = "timestamp") val timestamp: Instant
)

data class ArticleOverview(
    val articleId: String,
    val title: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val timestamp: Instant
)

data class ArticleDetails(
    val title: String,
    val author: String,
    val thumbnail: String,
    val content: String,
    val link: String
)