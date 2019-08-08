package offline.open.repository

import androidx.room.*
import offline.open.models.ArticleData
import offline.open.models.ArticleDetails
import offline.open.models.ArticleOverview

@Dao
interface ArticleDao {

    @Query("SELECT article_id, title, author, thumbnail, description, timestamp FROM articles")
    suspend fun fetchOverview(): List<ArticleOverview>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticles(articleData: List<ArticleData>)

    @Query("SELECT title, author, thumbnail, content, link FROM articles WHERE article_id = :articleId")
    suspend fun fetchArticle(articleId: String): ArticleDetails
}