package offline.open

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import offline.open.repository.ArticleDatabase

fun provideInMemoryDao() =
    Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), ArticleDatabase::class.java
    ).build().articleDao()