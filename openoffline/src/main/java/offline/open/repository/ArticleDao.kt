package offline.open.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import offline.open.models.ArticleData
import offline.open.models.ArticleOverview

@Dao
abstract class ArticleDao {

    @Query("SELECT article_id, title, author, thumbnail, description, timestamp FROM articles")
    abstract suspend fun fetchOverview(): List<ArticleOverview>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertArticle(articleData: ArticleData): Int
}