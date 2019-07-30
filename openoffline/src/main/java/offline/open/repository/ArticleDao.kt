package offline.open.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import offline.open.models.ArticleData
import offline.open.models.ArticleDetails
import offline.open.models.ArticleOverview

@Dao
abstract class ArticleDao {

    @Query("SELECT article_id, title, author, thumbnail, description, timestamp FROM articles")
    abstract suspend fun fetchOverview(): List<ArticleOverview>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertArticles(articleData: List<ArticleData>): Int

    @Query("SELECT title, author, thumbnail, content, link FROM articles WHERE article_id = :articleId")
    abstract suspend fun fetchArticle(articleId: String): ArticleDetails
}