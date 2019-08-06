package offline.open.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import offline.open.models.ArticleData

@Database(entities = [ArticleData::class], version = 1, exportSchema = true)
@TypeConverters(ListConverter::class, InstantConverter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao() : ArticleDao

    companion object {

        fun build(context: Context) =
            Room.databaseBuilder(context, ArticleDatabase::class.java, "articles.db")
                .build()
    }
}