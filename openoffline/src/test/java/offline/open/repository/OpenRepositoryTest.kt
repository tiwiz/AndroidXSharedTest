package offline.open.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenRepositoryTest {
    private val mockFeedUpdater: FeedUpdater = mock()
    private lateinit var inMemoryDao: ArticleDao
    private val styleWrapper = StyleWrapper()

    private val repository by lazy { OpenRepository(inMemoryDao, mockFeedUpdater, styleWrapper) }

    @Before
    fun setUp() {
        inMemoryDao = Room.inMemoryDatabaseBuilder(
            getApplicationContext(), ArticleDatabase::class.java
        ).build().articleDao()
    }

    @Test
    fun when_content_should_be_updated_feed_updater_should_manage_it() {
        runBlocking {
            repository.updateContent()
            verify(mockFeedUpdater, times(1)).updateContent()
        }
    }

    @Test
    fun if_dao_is_empty_content_should_be_downloaded_and_shown() {
        runBlocking {
            whenever(mockFeedUpdater.updateContent()).doAnswer {
                runBlocking {
                    inMemoryDao.insertArticles(listOf(SampleData.dbArticle1))
                }
            }

            assertThat(repository.getOverview()).containsOnly(SampleData.dbArticleOverview)

            verify(mockFeedUpdater, times(1)).updateContent()
        }
    }

    @Test
    fun if_dao_is_not_empty_it_should_show_the_content_without_fetching_from_network() {
        runBlocking {
            inMemoryDao.insertArticles(listOf(SampleData.dbArticle1))
            assertThat(repository.getOverview()).containsOnly(SampleData.dbArticleOverview)

            verify(mockFeedUpdater, never()).updateContent()
        }
    }

    @Test
    fun when_article_is_fetched_it_should_be_returned_and_styled() {
        runBlocking {
            inMemoryDao.insertArticles(listOf(SampleData.dbArticle1))

            assertThat(repository.getArticleDetails(SampleData.dbArticle1.articleId))
                .isEqualTo(SampleData.styledArticleDetails)
        }
    }
}