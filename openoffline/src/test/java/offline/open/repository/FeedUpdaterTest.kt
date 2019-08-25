package offline.open.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNullOrEmpty
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import offline.open.network.FeedApi
import offline.open.provideInMemoryDao
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedUpdaterTest {

    private val mockApi: FeedApi = mock()
    private lateinit var inMemoryDao: ArticleDao
    private val parser = FeedParser()

    private val feedUpdater by lazy { FeedUpdater(mockApi, inMemoryDao, parser) }

    @Before
    fun setUp() {
        inMemoryDao = provideInMemoryDao()
    }

    @Test
    fun when_downloaded_code_is_parsed_and_written_on_db() {
        runBlocking {
            whenever(mockApi.fetch()) doReturn SampleData.openFeed

            feedUpdater.updateContent()
            assertThat(inMemoryDao.fetchOverview()).containsOnly(SampleData.dbArticleOverview)
        }
    }

    @Test(expected = RuntimeException::class)
    fun when_code_is_not_downloaded_it_is_not_written_in_the_database() {
        runBlocking {
            whenever(mockApi.fetch()) doThrow RuntimeException()

            try {
                feedUpdater.updateContent()
            } finally {
                assertThat(inMemoryDao.fetchOverview()).isNullOrEmpty()
            }
        }
    }
}